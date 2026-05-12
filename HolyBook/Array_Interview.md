/*
╔══════════════════════════════════════════════════════════════════════════════╗
║            ARRAY  —  INTERVIEW NOTES  (Sameer's DSA Diary)                 ║
║                                                                              ║
║  HOW TO USE THIS FILE                                                        ║
║  ─────────────────────────────────────────────────────────────────────────  ║
║  • Read ONLY the QUESTIONS block first.                                      ║
║  • Write your answer on paper / in your head.                                ║
║  • Then scroll to ANSWERS to verify.                                         ║
║  • Add new Q&A blocks as you learn new patterns.                             ║
║                                                                              ║
║  COMPANION FILE:  Array_HolyBook.java  (problems, patterns, templates)       ║
╚══════════════════════════════════════════════════════════════════════════════╝
*/

// =============================================================================
//  HOW INTERVIEWS USUALLY GO FOR ARRAYS  (mental model)
// =============================================================================
/*
  STEP 1 — Clarify
    • Is the array sorted?          → Yes → consider Two Pointers before HashMap
    • Are values in range [1..n]?   → Yes → consider In-Place Negation (no extra space)
    • In-place required?            → Yes → no extra array; only O(1) extra vars
    • Can there be duplicates?      → affects HashSet vs HashMap vs counting

  STEP 2 — State brute force first (shows you understand the problem)
  STEP 3 — Identify the signal → pick the pattern (see Array_HolyBook Section 1)
  STEP 4 — Trace an example by hand BEFORE coding
  STEP 5 — Code, then walk through edge cases out loud
*/

// =============================================================================
//  SECTION 1 : CONCEPT QUESTIONS  (theory, no code)
// =============================================================================

// ─────────────────────────────────────────────────────────────────────────────
//  CQ1.  What is the Two Pointer pattern and when do you reach for it?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  Two Pointers keeps two index variables that move toward or across each other,
  eliminating the need for a nested loop.

  Use it when:
  ┌──────────────────────────────────────────────────────────────────┐
  │  Signal                         │  Pointer style                  │
  ├──────────────────────────────────────────────────────────────────┤
  │  Sorted array + find pair/sum   │  Opposite ends (left, right)    │
  │  In-place partition / filter    │  Slow-write + fast-read         │
  │  Palindrome / reverse           │  Opposite ends, move inward     │
  └──────────────────────────────────────────────────────────────────┘

  KEY RULE: On a sorted array, always ask "can I steer with two pointers?"
            before reaching for a HashMap.
*/

// ─────────────────────────────────────────────────────────────────────────────
//  CQ2.  What is a Sliding Window and when is it fixed vs variable?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  A window is a contiguous subarray we slide across the array without
  restarting from scratch each time (avoids O(n²) recomputation).

  Fixed window (size k):
    • Problem gives a hard size: "within distance k", "subarray of length k"
    • Evict the element that left: remove nums[i - k] when i >= k
    • Example: LC219 Contains Duplicates II

  Variable window:
    • Problem gives a value constraint: "sum ≤ target", "at most k distinct"
    • Expand right pointer; shrink left pointer when constraint is violated
    • Example: LC3 Longest Substring Without Repeating Characters
*/

// ─────────────────────────────────────────────────────────────────────────────
//  CQ3.  Explain the Boyer-Moore Voting Algorithm. When does it break?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  Maintain one candidate and one count.

    for each number:
      if count == 0  →  this number is the new candidate, count = 1
      else if num == candidate  →  count++
      else  →  count--

  Intuition: every "cancel" consumes one majority vote AND one minority vote.
  The majority element (> n/2 occurrences) has more votes than all others
  combined, so it always survives as the final candidate.

  IT BREAKS when a majority is not guaranteed.
  In that case: run the algorithm to get a candidate, then do a SECOND PASS
  to count occurrences and confirm it actually appears > n/2 times.

  Complexity: TIME O(n)  SPACE O(1)
*/

// ─────────────────────────────────────────────────────────────────────────────
//  CQ4.  What is In-Place Index Negation and when can you use it?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  When the array has size n and all values are in [1..n], you can map each
  value to an index (val - 1) and mark it as "visited" by negating that slot.

  Steps:
    1. For each num, compute idx = Math.abs(num) - 1
    2. If nums[idx] > 0, negate it  (mark visited)
    3. After marking: any index i with nums[i] > 0 means (i+1) was NEVER seen

  Signal to look for: "integers in range [1..n]" + "array of size n"
                      + "O(1) space required"

  Use Math.abs() because the value at an index may already be negated from
  a previous step.

  Problems: LC448 (find disappeared), LC442 (find duplicates)
*/

// ─────────────────────────────────────────────────────────────────────────────
//  CQ5.  What is XOR and why does it find the single unpaired number?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  XOR (^) properties:
    a ^ a = 0       (same number cancels itself)
    a ^ 0 = a       (XOR with zero is identity)
    XOR is commutative and associative (order doesn't matter)

  If every number appears twice except one, XOR-ing all elements together
  cancels every pair to 0, leaving only the lone number.

  Example: [4, 1, 2, 1, 2]
    4 ^ 1 ^ 2 ^ 1 ^ 2 = 4 ^ (1^1) ^ (2^2) = 4 ^ 0 ^ 0 = 4  ✓

  Missing number variant (LC268):
    XOR indices 0..n with all array values.
    The missing index doesn't get cancelled → it remains.
*/

// ─────────────────────────────────────────────────────────────────────────────
//  CQ6.  HashMap vs Sorting — when do you pick which?
// ─────────────────────────────────────────────────────────────────────────────
/*
  ANSWER:
  ┌────────────────────┬───────────────────────┬───────────────────────┐
  │                    │  HashMap / HashSet     │  Sort first           │
  ├────────────────────┼───────────────────────┼───────────────────────┤
  │  Time              │  O(n)                 │  O(n log n)           │
  │  Space             │  O(n)                 │  O(1) if in-place     │
  │  Array sorted?     │  Doesn't matter       │  Required (or you sort)│
  │  Order preserved?  │  Yes (insertion order)│  No (sort changes it) │
  │  Best for          │  Unsorted, O(n) needed│  Sorted, O(1) space   │
  └────────────────────┴───────────────────────┴───────────────────────┘

  Decision rule:
    Sorted input  →  try Two Pointers (O(n) O(1)) BEFORE HashMap
    Unsorted      →  HashMap is usually optimal
    Space budget  →  if O(1) space required, consider Sort or In-Place tricks
*/

// =============================================================================
//  SECTION 2 : PROBLEM-SPECIFIC INTERVIEW QUESTIONS
// =============================================================================
/*
  Read Q blocks → answer mentally → reveal A blocks.
  ─────────────────────────────────────────────────────────────────────────────
*/

// ─── Q1 ──────────────────────────────────────────────────────────────────────
/*
  Q: You have an UNSORTED array. Find if any duplicate exists.
     State time and space complexity.

  THINK FIRST ↓
  .
  .
  .
  A: HashSet approach.
     Iterate; if set.add(num) returns false → duplicate found, return true.
     TIME: O(n)   SPACE: O(n)

     Sorted alternative: sort, then check nums[i] == nums[i+1].
     TIME: O(n log n)  SPACE: O(1)  — pay more time to save space.
*/

// ─── Q2 ──────────────────────────────────────────────────────────────────────
/*
  Q: Same problem but array IS sorted. How do you do it in O(1) space?

  THINK FIRST ↓
  .
  .
  .
  A: Single pass — compare adjacent elements.
     for (int i = 1; i < n; i++) if (nums[i] == nums[i-1]) return true;
     TIME: O(n)  SPACE: O(1)
     Sorted = neighbors are comparable. No extra structure needed.
*/

// ─── Q3 ──────────────────────────────────────────────────────────────────────
/*
  Q: Given a SORTED array, find two numbers that add to a target.
     Interviewer: "Can you do it in O(1) space?"

  THINK FIRST ↓
  .
  .
  .
  A: Two Pointers — opposite ends.
     left = 0, right = n-1.
     sum < target → left++  (need bigger number)
     sum > target → right-- (need smaller number)
     sum == target → return {left+1, right+1}  (LC167 is 1-indexed)
     TIME: O(n)  SPACE: O(1)

     Why not HashMap? HashMap works but costs O(n) space.
     Sorted array lets you STEER, so you don't need a lookup table.
*/

// ─── Q4 ──────────────────────────────────────────────────────────────────────
/*
  Q: What's the difference between LC1 (Two Sum) and LC167 (Two Sum II)?
     Why does one use HashMap and the other Two Pointers?

  THINK FIRST ↓
  .
  .
  .
  A: LC1  — UNSORTED. You can't steer. You need O(1) lookup → HashMap.
     LC167 — SORTED.   You CAN steer. Two Pointers → O(1) space.

     Golden rule: sorted input → try Two Pointers FIRST, then fall back to HashMap.
*/

// ─── Q5 ──────────────────────────────────────────────────────────────────────
/*
  Q: Array with values in [1..n], size n. Find ALL missing numbers.
     Do it in O(n) time and O(1) space.

  THINK FIRST ↓
  .
  .
  .
  A: In-Place Negation (P10).
     Pass 1: for each num, negate nums[abs(num) - 1]  (mark that index visited)
     Pass 2: collect every index i where nums[i] > 0 → i+1 is missing

     The array itself is the hash table. Negation = "visited" flag.
     TIME: O(n)  SPACE: O(1) (output list doesn't count)
*/

// ─── Q6 ──────────────────────────────────────────────────────────────────────
/*
  Q: Array where every number appears twice except one. Find the loner.
     Do it in O(n) time and O(1) space (no sorting).

  THINK FIRST ↓
  .
  .
  .
  A: XOR all elements together.
     a ^ a = 0, so all pairs vanish. a ^ 0 = a, so the loner remains.
     int result = 0; for (int n : nums) result ^= n; return result;
     TIME: O(n)  SPACE: O(1)

     HashSet also works but is O(n) space. XOR is the expected answer here.
*/

// ─── Q7 ──────────────────────────────────────────────────────────────────────
/*
  Q: LC219 — Contains Duplicates within distance k.
     Walk me through your approach.

  THINK FIRST ↓
  .
  .
  .
  A: Sliding window of size k using a HashSet.
     - Maintain a set representing the current window of at most k elements.
     - For each index i:
         if (set.contains(nums[i])) return true;   // dup inside window
         set.add(nums[i]);
         if (set.size() > k) set.remove(nums[i - k]);  // evict oldest
     TIME: O(n)  SPACE: O(k)

     Key insight: window is [i-k+1 .. i]. When i == k, evict index 0 = nums[i-k].
*/

// ─── Q8 ──────────────────────────────────────────────────────────────────────
/*
  Q: How is "Move Zeroes" the same pattern as "Remove Duplicates from Sorted"
     and "Remove Element"? What do they share?

  THINK FIRST ↓
  .
  .
  .
  A: All three use the WRITE POINTER pattern (slow+fast two pointers).

     int write = 0;
     for (int read = 0; read < n; read++) {
         if (KEEP_CONDITION) nums[write++] = nums[read];
     }

     Only the keep condition differs:
       LC27  Remove Element            → nums[read] != val
       LC26  Remove Dups (Sorted)      → nums[read] != nums[write-1]
       LC283 Move Zeroes               → nums[read] != 0
                                         (then fill tail with 0s)
*/

// ─── Q9 ──────────────────────────────────────────────────────────────────────
/*
  Q: Input [9, 9, 9]. Plus One should give [1, 0, 0, 0]. How do you handle it?

  THINK FIRST ↓
  .
  .
  .
  A: Traverse right to left.
     - digit < 9 → increment and return immediately (early exit).
     - digit == 9 → set to 0 and continue (carry propagates).
     - If loop completes (all were 9s) → return new int[digits.length + 1]
       with result[0] = 1 (Java zero-initialises the rest automatically).

     TIME: O(n)  SPACE: O(1) amortized (O(n) only for all-9s edge case)
*/

// ─── Q10 ─────────────────────────────────────────────────────────────────────
/*
  Q: Missing number in [0..n] with exactly one missing. Best approach?

  THINK FIRST ↓
  .
  .
  .
  A: Two clean O(n) O(1) solutions:

     1. Gauss formula (P8 Math):
        expected = n * (n + 1) / 2;
        return expected - Arrays.stream(nums).sum();

     2. XOR (P9 Bit):
        int xor = 0;
        for (int i = 0; i <= n; i++) xor ^= i;
        for (int num : nums)         xor ^= num;
        return xor;
        (XOR all indices 0..n with all values; the missing index doesn't cancel)

     HashSet approach is O(n) space — correct but not expected in interviews.
     Show Gauss or XOR to impress.
*/

// =============================================================================
//  SECTION 3 : QUICK-FIRE BEHAVIOURAL / FOLLOW-UP QUESTIONS
// =============================================================================
/*
  These are the follow-ups interviewers add after you code a solution.
  Practise saying these out loud — silence here looks bad.

  ┌───────────────────────────────────────────────────────────────────────────┐
  │ After ANY solution:                                                       │
  │   "What is your time and space complexity?"                               │
  │   "What edge cases should we test?"                                       │
  │   "Can you do better on time? On space?"                                  │
  │   "What if the array is very large and doesn't fit in memory?"            │
  └───────────────────────────────────────────────────────────────────────────┘

  ┌───────────────────────────────────────────────────────────────────────────┐
  │ After HashMap solution:                                                   │
  │   "Can we reduce the space to O(1)?"                                      │
  │   "What if the array were sorted — would you change your approach?"       │
  └───────────────────────────────────────────────────────────────────────────┘

  ┌───────────────────────────────────────────────────────────────────────────┐
  │ After Two Pointer solution:                                               │
  │   "Why does this work on a sorted array but not an unsorted one?"         │
  │   "What would break if the array had duplicates?"                         │
  └───────────────────────────────────────────────────────────────────────────┘

  ┌───────────────────────────────────────────────────────────────────────────┐
  │ After In-Place Negation:                                                  │
  │   "Are you modifying the input? Is that acceptable?"                      │
  │   "What if values were not in [1..n]? Would this still work?"             │
  └───────────────────────────────────────────────────────────────────────────┘

  ┌───────────────────────────────────────────────────────────────────────────┐
  │ After Boyer-Moore:                                                        │
  │   "What if no majority element is guaranteed? How do you verify?"         │
  │   "What if majority is defined as > n/3? How would the algorithm change?" │
  └───────────────────────────────────────────────────────────────────────────┘
*/

// =============================================================================
//  SECTION 4 : COMPANIES & WHICH PATTERNS THEY FAVOUR
// =============================================================================
/*
  Amazon   → Two Pointers, Sliding Window, in-place array tricks (OA heavy)
  Google   → Math / Bit tricks, Boyer-Moore, follow-up optimisations
  Meta     → In-Place Negation, XOR, array manipulation at scale
  Microsoft→ Write Pointer, basic HashMap, sorted array Q&A
  Adobe    → Majority element, XOR, HashSet problems

  NOTE: These are tendencies, not guarantees. Pattern recognition > company prep.
*/

// =============================================================================
//  SECTION 5 : CHEAT-SHEET — PATTERN → SIGNAL → COMPLEXITY
// =============================================================================
/*
  ┌────────────────────┬──────────────────────────────────────┬─────────────────────┐
  │  Pattern           │  Signal in problem                   │  Complexity         │
  ├────────────────────┼──────────────────────────────────────┼─────────────────────┤
  │  P2  HashMap       │  "find", "exists", unsorted          │  O(n) T / O(n) S    │
  │  P4  Two Pointers  │  sorted array, in-place partition    │  O(n) T / O(1) S    │
  │  P5  Sliding Win.  │  "within k", "subarray of length k"  │  O(n) T / O(k) S    │
  │  P7  Boyer-Moore   │  "majority > n/2", guaranteed exists │  O(n) T / O(1) S    │
  │  P8  Math/Formula  │  sequence 0..n, digit manipulation   │  O(n) T / O(1) S    │
  │  P9  XOR/Bit       │  pairs cancel, single/missing number │  O(n) T / O(1) S    │
  │  P10 In-Place Neg. │  values in [1..n], size n, O(1) S   │  O(n) T / O(1) S    │
  └────────────────────┴──────────────────────────────────────┴─────────────────────┘
*/

// =============================================================================
//  [ ADD NEW Q&A BLOCKS IN SECTION 2 AS YOU LEARN NEW PATTERNS ]
//  Last updated: 2026-05-12  |  Questions: 10  |  Concepts: 6
//  Companion: Array_HolyBook.java
// =============================================================================
