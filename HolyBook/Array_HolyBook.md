/*
╔══════════════════════════════════════════════════════════════════════════════╗
║                     ARRAY  —  HOLY BOOK  (Sameer's DSA Diary)              ║
║                                                                              ║
║  HOW TO USE THIS FILE                                                        ║
║  ─────────────────────────────────────────────────────────────────────────  ║
║  • Read the PATTERN GLOSSARY first — every problem maps to a pattern.        ║
║  • After solving a problem, come here and log:                               ║
║      //My Approach   — what you actually coded                               ║
║      //Optimal       — the best known approach                               ║
║      //Pattern       — which pattern family it belongs to                    ║
║      //Revisit Needed -- REASON                                              ║
║      //What I Thought vs What I Should Have Used                             ║
║      //Trick         — the key "aha" insight                                 ║
║      //Edge Cases    — inputs that break naive solutions                     ║
║  • Interview Q&A lives at the BOTTOM — review before any interview.         ║
╚══════════════════════════════════════════════════════════════════════════════╝
*/

// =============================================================================
//  SECTION 0 : COMMENT LEGEND  (use these tags consistently in ALL files)
// =============================================================================
/*
  //My Approach  : what you first wrote / thought
  //Optimal      : best known solution — TIME: O(?) SPACE: O(?)
  //Pattern      : the pattern family (see Section 1)
  //Trick        : the single key insight that unlocks the optimal solution
  //Revisit Needed -- <REASON>   ← paste this when you struggled / got it wrong
  //What I Thought vs What I Should Have Used -- <before> → <after>
  //Edge Cases   : list inputs that can break your code
  //Why It Works : brief proof / intuition for the approach
  //Asked In     : company / round where this problem type appeared
*/

// =============================================================================
//  SECTION 1 : PATTERN GLOSSARY FOR ARRAYS
// =============================================================================
/*
  P1  — Brute Force (nested loops)             O(n²) time — baseline, never ship
  P2  — HashMap / HashSet (trade space for O(1) lookup)
  P3  — Sorting first (makes neighbors comparable)  — costs O(n log n)
  P4  — Two Pointers (left+right or slow+fast)  — works best on SORTED arrays or in-place
  P5  — Sliding Window (fixed or variable window over array)
  P6  — Prefix Sum (running sum lets you answer range queries in O(1))
  P7  — Greedy / Counting (track one candidate and commit)
  P8  — Math / Formula  (use arithmetic instead of iteration)
  P9  — Bit Manipulation (XOR, AND tricks for duplicate / missing)
  P10 — In-Place Index Marking (use the array itself as a hash — mark visited by negating)

  WHEN TO THINK WHAT:
  ┌───────────────────────────────────┬───────────────────────────────┐
  │ Signal in the problem             │ Pattern to try first          │
  ├───────────────────────────────────┼───────────────────────────────┤
  │ "find two numbers that …"         │ P4 (sorted) or P2 (unsorted)  │
  │ "duplicates / unique"             │ P2 HashSet, or P9 XOR         │
  │ "within window of k"              │ P5 Sliding Window             │
  │ "majority / appears > n/2"        │ P7 Boyer-Moore Vote           │
  │ "range [1..n], values in [1..n]"  │ P10 In-Place Negation         │
  │ "sorted array"                    │ P4 Two Pointers first!        │
  │ "sum of range / subarray"         │ P6 Prefix Sum                 │
  │ "missing number, single pass"     │ P8 Gauss or P9 XOR            │
  │ "move / partition in-place"       │ P4 slow+fast pointer          │
  └───────────────────────────────────┴───────────────────────────────┘
*/

// =============================================================================
//  SECTION 2 : PROBLEMS LOG
//  (Each problem = mini case-study. Add new ones at the top of this section.)
// =============================================================================

// ─────────────────────────────────────────────────────────────────────────────
// [LC 167] Two Sum II — Input Array is Sorted                  STATUS: ⬜ TODO  | Not Attempted Yet
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Sorted array (1-indexed). Find two indices s.t. nums[i] + nums[j] == target.
    Exactly one solution. Cannot use the same element twice.

  //My Approach    : (fill this in after you attempt it)

  //What I Thought vs What I Should Have Used
    → Temptation: reuse HashMap from LC1 (P2)
    → But wait — the array is already SORTED. What does that tell you?
    → Sorted = you can *steer* toward the target. Think: if sum > target, move which pointer?

  //Pattern        : P4 — Two Pointers (left=0, right=n-1, no extra space)

  //Optimal        : TIME: O(n)  SPACE: O(1)
  //Trick          : On a sorted array, two pointers let you eliminate half the search
                     space at every step.  No HashMap needed → O(1) space.

  //Why It Works:
    left points at the smallest, right at the largest.
    sum < target → we need a bigger number  → move left RIGHT
    sum > target → we need a smaller number → move right LEFT
    sum == target → done!

  //Edge Cases     : negative numbers (still works — sorted is sorted),
                     target at boundaries, single pair

  //Revisit Needed -- Make sure you can code this without looking. Two-pointer
                      is asked ALL THE TIME on sorted-array problems.

  //Asked In       : Amazon, Google, Meta (sorted array + two sum variant)
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 1] Two Sum — Unsorted              STATUS: ✅ Done | 13-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : HashMap<value, index>. For each num, check if (target-num)
                     already in map. One pass.
  //Optimal        : TIME: O(n)  SPACE: O(n)  ← your HashMap solution IS optimal

  //Pattern        : P2 — HashMap

  //Trick          : Store what you've SEEN so far (not what you need).
                     Check "complement = target - current" in the map.
                     Single pass works because if A+B=target, when you reach B,
                     A is already in the map.

  //What I Thought vs What I Should Have Used
    → You correctly jumped past brute force to HashMap. 
    → Key insight to remember: "I need a fast lookup of what I've seen" → HashMap.

  //Edge Cases     : duplicate values (map stores index, duplicates still work
                     because you check BEFORE inserting), nums.length < 2

  //Pattern Note   : LC167 (sorted) → P4 Two Pointers (O(1) space).
                     LC1   (unsorted) → P2 HashMap (O(n) space).
                     Same problem, sorted vs unsorted = completely different pattern!
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 217] Contains Duplicates           STATUS: ✅ Done | 15-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : HashSet. If set.add() returns false → duplicate found.
  //Optimal        : TIME: O(n)  SPACE: O(n)  ← your solution IS optimal

  //Pattern        : P2 — HashSet

  //Trick          : set.add() returns false if element already exists.
                     Use that boolean directly instead of calling contains() first.
                     (contains + add = two operations; add-and-check = one)

  //What I Thought vs What I Should Have Used
    → You first tried sorting (P3) — correct logic but O(n log n).
    → Then you upgraded to HashSet O(n). 
    → Recognizing "I tried the expensive version first, then optimized" is great habit.

  //Edge Cases     : empty array, single element, all same elements
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 219] Contains Duplicates II — within window k  STATUS: ✅ Done | 15-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : Sliding window of size k using HashSet.
                     Add current, remove element that left the window (nums[i-k]).
  //Optimal        : TIME: O(n)  SPACE: O(k)  ← your solution IS optimal

  //Pattern        : P5 — Sliding Window  +  P2 HashSet inside the window

  //Trick          : The window has a FIXED maximum size (k).
                     When window grows beyond k, evict the oldest element: nums[i - k].
                     If you see a duplicate inside the window = the indices are ≤ k apart.

  //What I Thought vs What I Should Have Used
    → You correctly identified this as sliding window. 
    → The key signal you should memorize: "within distance k" → sliding window of size k.

  //Edge Cases     : k=0 (window size 0, always false), k >= n, all same elements

  //Revisit Needed -- Make sure you remember WHY we remove nums[i-k] and not
                      nums[i-k-1]. Draw it out: window is [i-k+1 .. i], size = k.
                      When i=k, remove index 0 = nums[i-k].
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 169] Majority Element              STATUS: ✅ Done | 13-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : Sort → count consecutive. Also tried HashMap with getOrDefault.
  //Optimal        : TIME: O(n)  SPACE: O(1)  — Boyer-Moore Voting Algorithm

  //Pattern        : P7 — Greedy / Voting  (your sort = P3, your HashMap = P2)

  //Trick (Boyer-Moore):
    Maintain a `candidate` and a `count`.
    For each number:
      if count == 0  → this number becomes the new candidate
      if num == candidate → count++
      else → count--
    The majority element (appears > n/2 times) will always survive as candidate.

  //Why It Works   : Every time the candidate is "cancelled" by a different number,
                     you consume one of theirs AND one of yours. The majority element
                     has more than all others combined, so it survives.

  //What I Thought vs What I Should Have Used
    → You used sort (O(n log n)) and HashMap (O(n) space).
    → Boyer-Moore uses O(1) space and O(n) time. 
    → The signal: "majority element ALWAYS exists" + "appears > n/2" → Boyer-Moore.

  //Revisit Needed -- Boyer-Moore is a classic interview trick. You MUST be able
                      to code it from memory. Trace through [2,2,1,1,1,2,2] by hand
                      before your next session.

  //Edge Cases     : single element, all same, alternating (still works)

  //Asked In       : Amazon, Adobe, Uber
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 283] Move Zeroes                   STATUS: ✅ Done | 15-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : Write pointer k. Copy non-zeros forward, fill tail with 0s.
  //Optimal        : TIME: O(n)  SPACE: O(1)  ← your solution IS optimal

  //Pattern        : P4 — Two Pointers (slow writer + fast reader)

  //Trick          : The "write pointer" pattern:
                     slow pointer k = next position to write a non-zero
                     fast pointer i = scans the whole array
                     After loop: everything from k..n-1 gets zeroed.

  //What I Thought vs What I Should Have Used
    → You nailed the two-pass version (copy non-zeros, fill zeros). Optimal.
    → A one-pass swap version also exists:
        if (nums[i] != 0) swap(nums[i], nums[k++])
      Both are O(n) O(1); the swap version does fewer writes when zeros are rare.

  //Edge Cases     : all zeros, no zeros, single element, zeros at end already
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 268] Missing Number                STATUS: ✅ Done | 18-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : HashSet approach (O(n) space), then Gauss formula (O(1) space).
  //Optimal        : TIME: O(n)  SPACE: O(1)  — Gauss OR XOR

  //Pattern        : P8 — Math/Formula  (Gauss sum)  or  P9 — Bit Manipulation (XOR)

  //Trick (Gauss)  : expected = n*(n+1)/2.  missing = expected - actual_sum.
  //Trick (XOR)    : XOR all indices 0..n  XOR all nums.
                     Duplicates cancel (a ^ a = 0). Leftover = missing number.

  //What I Thought vs What I Should Have Used
    → You started with HashSet (good thinking), then optimized to Gauss (great!).
    → The XOR version is the "show-off" answer — O(1) space, no overflow risk.
    → Your progression (brute → set → formula) is the right learning path!

  //Edge Cases     : missing 0, missing n, single element array
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 448] Find All Disappeared Numbers  STATUS: ✅ Done | 18-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  //My Approach    : HashSet (O(n) space), boolean[] array (O(n) space).
  //Optimal        : TIME: O(n)  SPACE: O(1)  — In-Place Index Negation

  //Pattern        : P10 — In-Place Index Marking

  //Trick (In-Place Negation):
    Values are in [1..n] and indices are [0..n-1].
    For each num, negate the value at index (abs(num) - 1).
    After marking: any index i where nums[i] > 0 means (i+1) was never visited.
    That index+1 is a missing number.

  //Why It Works   : The array doubles as a "visited" hash.
                     Since values map to valid indices, we mark by negating.
                     We use abs() when reading to handle already-negated values.

  //What I Thought vs What I Should Have Used
    → You used HashSet and boolean[] — both O(n) space, both correct.
    → The O(1) space trick: "values in [1..n], array size n" → use the array itself
      as your hash table by negating at the mapped index.
    → Memorize this signal: range [1..n] + size n → In-Place Negation possible.

  //Revisit Needed -- The in-place negation trick is not obvious. Practice coding it
                      from scratch. It appears in LC442 (find duplicates) too.

  //Asked In       : Facebook, Google
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 136] Single Number              STATUS: ✅ Done | 18-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Every element appears twice EXCEPT one. Find that one.
    Constraints: linear runtime, no extra memory.

  //My Approach    : XOR Bitwise. XOR all elements — pairs cancel (a^a=0),
                     lone element remains (a^0=a). Recognised this as the ONLY
                     clean O(1)-space solution right away.
  //Optimal        : TIME: O(n)  SPACE: O(1)  ← your XOR solution IS optimal ✅

  //Pattern        : P9 — Bit Manipulation (XOR)

  //Trick          : a ^ a = 0,   a ^ 0 = a.
                     XOR every number together. All pairs annihilate; the loner survives.
                     Order doesn't matter — XOR is commutative and associative.

  //Why It Works   : If every number appeared twice the total XOR would be 0.
                     The single unpaired number is what's left over.

  //What I Thought vs What I Should Have Used
    → You went straight to XOR — that's the optimal answer.
    → The common wrong path is HashSet (O(n) space). You skipped it. 
    → Revisit: can you explain WHY XOR works to an interviewer without notes?

  //Edge Cases     : single element array, all duplicates except last element,
                     negative numbers (XOR still works — just bit patterns)

  //Revisit Needed -- You solved it correctly BUT revisit to make sure you can
                      EXPLAIN the XOR property out loud. Interviewers always ask "why".

  //Asked In       : Amazon, Microsoft, Adobe
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 485] Max Consecutive Ones       STATUS: ✅ Done | 18-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Binary array. Find the maximum number of consecutive 1s.

  //My Approach    : Used two variables K (current streak) and T (max seen so far).
                     When a 0 is hit, compare K vs T and swap if K is bigger, then reset K.
  //Optimal        : TIME: O(n)  SPACE: O(1)

  //Pattern        : P4 — Two Pointers / Simple Counter (single pass)

  //Trick          : One variable tracks current run, one tracks the global max.
                     On every 0: update max = Math.max(max, current); reset current = 0.
                     CRITICAL: after the loop ends, do a final max = Math.max(max, current)
                     to catch a streak that runs to the very end of the array.

  //What I Thought vs What I Should Have Used
    → Your K/T swap approach is correct in logic.
    → The cleaner pattern: don't "swap" — just use Math.max() at zero and at end.
    → The #1 edge-case mistake here: forgetting the final max check AFTER the loop.
        e.g., [1,1,1] → loop ends without hitting a 0 → must check after loop!

  //Edge Cases     : all 1s (no zero encountered — need post-loop check!),
                     all 0s (max stays 0), single element

  //Revisit Needed -- You used manual swap instead of Math.max. Practice the
                      cleaner one-liner style. Also trace [1,1,1] — does your
                      code return 3 without the post-loop check?

  //Asked In       : Facebook, Amazon (warm-up round)
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 26] Remove Duplicates from Sorted Array  STATUS: ✅ Done | 13-Apr-2026 | Revisit: Yes
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Sorted array in-place. Remove duplicates so each unique appears once.
    Return the count k of unique elements. First k elements of array must be correct.

  //My Approach    : Loop, checked if next element equals current. If same, skip;
                     if different, swap/write it to the next slot.
  //Optimal        : TIME: O(n)  SPACE: O(1)  ← your approach IS optimal in complexity

  //Pattern        : P4 — Two Pointers (slow writer `k`, fast reader `i`)

  //Trick          : The write pointer pattern — same family as Move Zeroes (LC283).
                     k = next position to write a unique value.
                     When nums[i] != nums[k-1], write nums[i] into nums[k], then k++.
                     No swapping needed — just overwrite forward.

  //What I Thought vs What I Should Have Used
    → You checked next element and swapped positions (correct logic, extra swaps).
    → Optimal: write forward, don't swap. nums[k++] = nums[i] is cleaner.
    → Key difference: swap moves two values; write-pointer only moves one.
    → Compare with LC283 (Move Zeroes) — identical write-pointer skeleton!

  //Edge Cases     : empty array, all duplicates ([1,1,1] → k=1), no duplicates,
                     single element

  //Revisit Needed -- You used swap instead of the cleaner write-pointer.
                      Practice: write the two-pointer version WITHOUT swap.
                      Template: k=1; for i=1..n: if nums[i]!=nums[k-1] → nums[k++]=nums[i]

  //Asked In       : Microsoft, Apple (in-place modification warm-up)
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 27] Remove Element              STATUS: ✅ Done | 13-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Given an array and a value `val`, remove all occurrences of `val` in-place.
    Return the count k of remaining elements.

  //My Approach    : Write pointer K. For each element, if it != val, write it
                     to position K and increment K (K++ to count and swap with position).
  //Optimal        : TIME: O(n)  SPACE: O(1)  ← your solution IS optimal ✅

  //Pattern        : P4 — Two Pointers (write pointer)

  //Trick          : Same write-pointer skeleton as LC26 and LC283.
                     The "keep" condition is: nums[i] != val.
                     Write pointer K tracks where the next "kept" element goes.

  //What I Thought vs What I Should Have Used
    → You nailed the write-pointer directly. No adjustment needed.
    → Notice the family: LC27, LC26, LC283 all use IDENTICAL skeleton.
      Only the keep-condition changes: (!= val) / (unique) / (!= 0).

  //Edge Cases     : val not present (array unchanged, k = n),
                     all elements equal val (k = 0), empty array

  //Asked In       : Amazon (screening), Google (warm-up)
*/

// ─────────────────────────────────────────────────────────────────────────────
// [LC 66] Plus One                    STATUS: ✅ Done | 13-Apr-2026 | Revisit: No
// ─────────────────────────────────────────────────────────────────────────────
/*
  PROBLEM SNAPSHOT:
    Digits of a number stored as an array. Add one to the number. Return the result array.

  //My Approach    : Reverse loop. If digit == 9 → set to 0 and continue (carry).
                     First non-9 digit gets +1 and return immediately.
                     If loop completes (all 9s), prepend 1.
  //Optimal        : TIME: O(n)  SPACE: O(1) amortized (O(n) only for all-9s edge case)

  //Pattern        : P8 — Math / Carry Propagation

  //Trick          : Traverse from the RIGHT (least significant digit).
                     9 + 1 = 10 → digit becomes 0, carry propagates left.
                     If you find any digit < 9 → just increment it and STOP immediately.
                     Only ALL-9s input needs a new array (size n+1, first element 1).

  //What I Thought vs What I Should Have Used
    → Your reverse-loop approach IS the optimal solution.
    → Make sure you handle the all-9s case: [9,9,9] → [1,0,0,0].
      Use: int[] result = new int[digits.length + 1]; result[0] = 1;
      (All other slots are 0 by default in Java — no need to fill manually.)

  //Edge Cases     : [9] → [1,0],  [9,9,9] → [1,0,0,0],  [1,9] → [2,0]

  //Asked In       : Google, Amazon (array manipulation warm-up)
*/

// =============================================================================
//  SECTION 3 : PATTERNS — DEEPER DIVES WITH TEMPLATES
// =============================================================================

/*
  ── TWO POINTERS (P4) ────────────────────────────────────────────────────────

  Template A: Opposite ends (sorted array — Two Sum II style)
  ┌─────────────────────────────────────────────────────────────┐
  │  int left = 0, right = n - 1;                               │
  │  while (left < right) {                                     │
  │      int sum = nums[left] + nums[right];                    │
  │      if      (sum < target) left++;                         │
  │      else if (sum > target) right--;                        │
  │      else /* found */ break;                                │
  │  }                                                          │
  └─────────────────────────────────────────────────────────────┘

  Template B: Slow + Fast (write pointer — Move Zeroes / Remove Dups style)
  ┌─────────────────────────────────────────────────────────────┐
  │  int write = 0;                                             │
  │  for (int read = 0; read < n; read++) {                     │
  │      if (condition to keep nums[read]) {                    │
  │          nums[write++] = nums[read];                        │
  │      }                                                      │
  │  }                                                          │
  └─────────────────────────────────────────────────────────────┘

  ── SLIDING WINDOW (P5) ──────────────────────────────────────────────────────

  Fixed window size k:
  ┌─────────────────────────────────────────────────────────────┐
  │  Set<Integer> window = new HashSet<>();                     │
  │  for (int i = 0; i < n; i++) {                              │
  │      if (window.contains(nums[i])) return true;             │
  │      window.add(nums[i]);                                   │
  │      if (window.size() > k) window.remove(nums[i - k]);     │
  │  }                                                          │
  └─────────────────────────────────────────────────────────────┘

  ── BOYER-MOORE VOTING (P7) ──────────────────────────────────────────────────
  ┌─────────────────────────────────────────────────────────────┐
  │  int candidate = nums[0], count = 1;                        │
  │  for (int i = 1; i < n; i++) {                              │
  │      if (count == 0) { candidate = nums[i]; count = 1; }   │
  │      else if (nums[i] == candidate) count++;                │
  │      else count--;                                          │
  │  }                                                          │
  │  return candidate;                                          │
  └─────────────────────────────────────────────────────────────┘

  ── IN-PLACE NEGATION (P10) ──────────────────────────────────────────────────
  (Works when values are in range [1..n] and array size is n)
  ┌─────────────────────────────────────────────────────────────┐
  │  for (int i = 0; i < n; i++) {                              │
  │      int idx = Math.abs(nums[i]) - 1;  // map value to index│
  │      if (nums[idx] > 0) nums[idx] = -nums[idx]; // mark     │
  │  }                                                          │
  │  // Collect: index i where nums[i] > 0 → (i+1) is missing  │
  └─────────────────────────────────────────────────────────────┘
*/

// =============================================================================
//  SECTION 4 : MY PERSONAL MISTAKE LOG
//  (Add entries here every time you make a recurring mistake)
// =============================================================================
/*
  ❌ MISTAKE: Defaulting to HashMap/HashSet for everything.
     FIX    : Ask "is the array sorted?" first. Sorted → try Two Pointers before HashMap.

  ❌ MISTAKE: Forgetting 1-indexed vs 0-indexed (LC167 returns 1-indexed).
     FIX    : Read the output spec carefully. "return index + 1" if 1-indexed.

  ❌ MISTAKE: Using sort when only O(n) is needed.
     FIX    : Sorting always costs O(n log n). If you can do it in O(n), you should.

  ❌ MISTAKE: Missing the "values in [1..n]" signal for in-place tricks.
     FIX    : Whenever you see "integers in range [1..n]" + "array of size n" →
              immediately think P10 (In-Place Negation).

  ❌ MISTAKE: Not checking for null / empty input at method entry.
     FIX    : Always add guard: if (nums == null || nums.length == 0) return ...
*/

// =============================================================================
//  SECTION 5 : SUGGESTED NEXT PROBLEMS (ordered by concept)
// =============================================================================
/*
  ✅ = done  ⬜ = not started  ⚠️ = revisit

  WRITE POINTER (P4) — all done, recognize the pattern family:
    ✅ LC27   Remove Element              (13-Apr-2026) — keep != val
    ✅ LC26   Remove Duplicates (Sorted)  (13-Apr-2026) — keep unique  ← Revisit swap→write
    ✅ LC283  Move Zeroes                 (15-Apr-2026) — keep non-zero

  TWO POINTERS (P4) — opposite ends:
    ⬜ LC167  Two Sum II             ← DO THIS NEXT (sorted input!)
    ⬜ LC344  Reverse String
    ⬜ LC125  Valid Palindrome
    ⬜ LC15   3Sum                   ← harder, sort + two pointers

  SLIDING WINDOW (P5):
    ✅ LC219  Contains Duplicates II (15-Apr-2026) ← Revisit window eviction logic
    ⬜ LC643  Maximum Average Subarray I (fixed window)
    ⬜ LC3    Longest Substring Without Repeating Characters (variable window)

  PREFIX SUM (P6):
    ⬜ LC303  Range Sum Query
    ⬜ LC560  Subarray Sum Equals K

  BOYER-MOORE / COUNTING (P7):
    ✅ LC169  Majority Element (13-Apr-2026) ← Revisit: re-code Boyer-Moore from memory

  BIT MANIPULATION (P9):
    ✅ LC136  Single Number  (18-Apr-2026) ← Revisit: explain XOR out loud
    ✅ LC268  Missing Number (18-Apr-2026) ← Revisit: also know the XOR version

  IN-PLACE NEGATION (P10):
    ✅ LC448  Find All Disappeared Numbers (18-Apr-2026) — No revisit needed
    ⬜ LC442  Find All Duplicates in Array ← try the in-place negation version

  MATH / CARRY (P8):
    ✅ LC66   Plus One (13-Apr-2026) — No revisit needed

  HASHMAP / HASHSET (P2):
    ✅ LC1    Two Sum   (13-Apr-2026) — No revisit needed
    ✅ LC217  Contains Duplicates (15-Apr-2026) — No revisit needed
    ✅ LC485  Max Consecutive Ones (18-Apr-2026) ← Revisit: post-loop max check
*/

// =============================================================================
//  SECTION 6 : INTERVIEW PREP
// =============================================================================
/*
  ╔══════════════════════════════════════════════════════════════════╗
  ║  Interview Q&A has moved to its own file for better readability  ║
  ║                                                                  ║
  ║  →  Array_Interview.java  (same HolyBook/ folder)               ║
  ║                                                                  ║
  ║  Contents:                                                       ║
  ║    • How interviews go — mental model                            ║
  ║    • 6 Concept questions (Two Pointers, Sliding Window, XOR …)  ║
  ║    • 10 Problem-specific Q&A  (read Q → think → reveal A)       ║
  ║    • Follow-up questions interviewers add after your code        ║
  ║    • Company tendencies (Amazon / Google / Meta …)               ║
  ║    • Pattern → Signal → Complexity cheat-sheet                  ║
  ╚══════════════════════════════════════════════════════════════════╝
*/

// =============================================================================
//  [ ADD NEW PROBLEMS ABOVE SECTION 3 — keep this structure alive! ]
//  Last updated: 2026-05-12  |  Problems logged: 13  |  Next: LC167 Two Sum II
// =============================================================================
