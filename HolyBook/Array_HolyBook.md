# ARRAY — HOLY BOOK (Sameer's DSA Diary)

> **How to use this file**
> - Read the Pattern Glossary first — every problem maps to a pattern.
> - After solving, log: My Approach · Optimal · Pattern · Trick · Revisit · Edge Cases
> - Interview Q&A lives in `Array_Interview.md`
> - General tips (complexity, constraints, meta) live in `General_HolyBook.md`

---

## Section 0 — Comment Legend
Use these tags consistently in all files and in your solution files.

| Tag | What to write |
|---|---|
| `My Approach` | What you first wrote / thought |
| `Optimal` | Best known solution — TIME: O(?) SPACE: O(?) |
| `Pattern` | The pattern family (see Section 1) |
| `Trick` | The single key insight that unlocks the solution |
| `Revisit Needed -- <REASON>` | Paste this when you struggled or got it wrong |
| `What I Thought vs What I Should Have Used` | Before → After |
| `Edge Cases` | Inputs that can break your code |
| `Why It Works` | Short proof / intuition |
| `Asked In` | Company / round where this appeared |

---

## Section 1 — Pattern Glossary

| Code | Pattern | Cost | Notes |
|---|---|---|---|
| P1 | Brute Force (nested loops) | O(n²) | Baseline — say it, don't ship it |
| P2 | HashMap / HashSet | O(n) T / O(n) S | Trade space for O(1) lookup |
| P3 | Sort first | O(n log n) T | Makes neighbours comparable |
| P4 | Two Pointers (left+right or slow+fast) | O(n) T / O(1) S | Best on sorted or in-place |
| P5 | Sliding Window (fixed or variable) | O(n) T / O(k) S | Contiguous subarray problems |
| P6 | Prefix Sum | O(n) T / O(n) S | Range queries in O(1) after build |
| P7 | Greedy / Voting (Boyer-Moore) | O(n) T / O(1) S | Track one candidate and commit |
| P8 | Math / Formula | O(n) T / O(1) S | Gauss, carry, digit tricks |
| P9 | Bit Manipulation (XOR) | O(n) T / O(1) S | Pairs cancel — missing/single |
| P10 | In-Place Index Marking (negation) | O(n) T / O(1) S | Values [1..n], size n |

### Signal → Pattern Quick Map

| You see this in the problem | Try this first |
|---|---|
| "find two numbers that …" | P4 if sorted, P2 if not |
| "duplicates / unique" | P2 HashSet, or P9 XOR |
| "within window of k" | P5 Sliding Window |
| "majority / appears > n/2" | P7 Boyer-Moore |
| "range [1..n], values in [1..n]" | P10 In-Place Negation |
| "sorted array" | P4 Two Pointers first! |
| "sum of range / subarray" | P6 Prefix Sum |
| "missing number, single pass" | P8 Gauss or P9 XOR |
| "move / partition in-place" | P4 slow+fast pointer |

---

## Section 1B — Constraints Quick Reminder

> Full guide → `General_HolyBook.md`

| n size | Time complexity needed |
|---|---|
| n ≤ 1,000 | O(n²) ok |
| n ≤ 100,000 | O(n log n) max |
| n ≤ 1,000,000 | O(n) required |

| Signal | What it unlocks |
|---|---|
| values in [1..n] + size n | In-Place Negation (P10) |
| "sorted" | Two Pointers BEFORE HashMap |
| "in-place" / "O(1) space" | Only ~5 variables — no HashSet |
| "1-indexed" | return i+1, j+1 — **don't forget!** |

---

## Section 2 — Problems Log

> Add new problems at the TOP of this section.

---

### [LC 392] Is Subsequence
**Status:** ✅ Done | 13-May-2026 | **Revisit: Yes**
**Pattern:** P4 — Two Pointers across two strings (slow needle + fast haystack)
**Difficulty:** Easy | [Problem](http://lcid.cc/392)

**Problem:** Given strings `s` and `t`, return `true` if `s` is a subsequence of `t`.
All characters of `s` must appear in `t` in the same left-to-right order (not necessarily contiguous).

**My Approach**
- Counter `k` tracks progress through `s`. Loop `i` through `t`.
- When `t[i] == s[k]`, increment `k`. When `k == s.length()`, return true.

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Two Bugs I Had**

| Bug | What I wrote | What it should be |
|---|---|---|
| Wrong string scanned | `s.charAt(i)` — scanned pattern against itself | `t.charAt(i)` — scan the haystack |
| Off-by-one | `i < t.length()-1` — missed last char | `i < t.length()` |

The first bug caused `s` to always match itself — the code returned `true` for any `t`. It worked for `"ahbgdc"` only by accident (answer happens to be true).

**Cleaner Version**
```java
public static boolean isSubsequence(String s, String t) {
    if (s.isEmpty()) return true;   // s.charAt(k) crashes when s="" — guard needed
    int k = 0;
    for (int i = 0; i < t.length(); i++) {
        if (t.charAt(i) == s.charAt(k)) {
            k++;
            if (k == s.length()) return true;
        }
    }
    return false;
}
```

**The Two Cursors Mental Model**
```
t:  a  h  b  g  d  c     ← i walks this ALWAYS, every step
s:  a  b  c              ← k walks this ONLY on a match
```
- `i` = fast scanner (haystack). Always moves.
- `k` = slow matcher (needle). Only moves when `t[i] == s[k]`.
- When `k` reaches `s.length()` → all characters found in order → true.

**Dry Run — `s="abc"`, `t="ahcgdb"` (false)**
```
i=0 t[0]='a' == s[0]='a' ✓  k→1
i=1 t[1]='h' != s[1]='b'  skip
i=2 t[2]='c' != s[1]='b'  skip
i=3 t[3]='g' != s[1]='b'  skip
i=4 t[4]='d' != s[1]='b'  skip
i=5 t[5]='b' == s[1]='b' ✓  k→2
Loop ends. k=2 != s.length()=3 → return false ✓
('c' needed after 'b' but nothing left in t after index 5)
```

**Edge Cases**
- `s = ""` — empty string is always a subsequence of anything. `k==s.length()` is `0==0` → true. No guard needed.
- `t = ""`, `s` non-empty — loop never runs, `k` stays 0, `0 != s.length()` → false. Correct.
- `s == t` — every character matches in order → true.

**What I Thought vs What I Should Have Used**
- Mental model was right: one pointer per string
- Execution mistake: wrote `s.charAt(i)` when I meant `t.charAt(i)`. The variable name `i` was bound to the wrong string conceptually.
- Lesson: **always label your pointers mentally** — `i` is the `t` cursor, `k` is the `s` cursor. Don't mix them.

**Follow-Up: Multiple Queries (Binary Search approach)**
- If same `t`, many different `s` strings — preprocess `t` into a map of `char → sorted list of indices`
- For each char of `s`, binary search for next valid index in `t` (must be > last used index)
- Preprocessing: O(n) once. Each query: O(m log n) instead of O(m·n) naive
- For single query: your O(n) O(1) solution is already optimal. Cannot beat reading `t` once.

> **Revisit Needed:** Label your pointers before coding. `i` = haystack cursor, `k` = needle cursor. Write it as a comment first. Also know the binary search follow-up answer.

**Asked In:** Google, Facebook, Amazon (follow-up on multiple queries is a Google favourite)

---

**Status:** ✅ Done | 12-May-2026 | **Revisit: Yes**
**Pattern:** P4 — Two Pointers, opposite ends + in-loop skip
**Difficulty:** Medium | [Problem](http://lcid.cc/125)

**Problem:** Check if a string is a palindrome after removing non-alphanumeric characters and lowercasing.

---

**My Approach (first attempt)**
- Cleaned string first using `replaceAll("[^a-zA-Z0-9]", "").toLowerCase()`
- Then Two Pointed on the clean string
- TIME: O(n) SPACE: O(n) — new string created

**What was wrong with my first regex:**
- Originally wrote `[\\s,\\p{Punct}]` — removes whitespace + punctuation
- Should be `[^a-zA-Z0-9]` — removes anything that is NOT alphanumeric
- The `^` inside `[]` means NOT. Always think: *what do I want to KEEP*, not *what do I want to remove*

**Optimal Approach — Skip-Junk Two Pointers**
```java
int i = 0, j = s.length() - 1;
while (i < j) {
    while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;  // skip junk left
    while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;  // skip junk right
    if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
        return false;
    i++; j--;
}
return true;
```
**TIME: O(n) SPACE: O(1)** — no new string, just pointers skipping over junk

---

**The Key Mental Shift — How I Should Have Thought**

I asked: *"How do I remove junk?"* → led to creating a new string

Should have asked: *"Do I need to REMOVE junk, or can I just SKIP it?"*
→ Pointer is already moving. Just add a condition: move further if junk. No extra space needed.

---

**Dry Run — `"A,b,a"` (true)**
```
Index:  0   1   2   3   4
Char:  'A' ',' 'b' ',' 'a'
        i                j

Round 1: i=0, j=4
  skip left:  'A' is alphanumeric → i stays 0
  skip right: 'a' is alphanumeric → j stays 4
  compare: toLowerCase('A')='a' == 'a' ✓
  i++→1, j--→3

Round 2: i=1, j=3
  skip left:  ',' is junk → i++ → i=2, 'b' is ok → stop
  skip right: ',' is junk → j-- → j=2, 'b' is ok → stop
  now i=2, j=2 → i<j is FALSE → inner while stops
  compare: 'b' == 'b' ✓
  i++→3, j--→1

Round 3: outer while i<j → 3<1 FALSE → exit
return true ✓
```

**Dry Run — `"race a car"` (false)**
```
Round 4: i=3('e'), j=6(' ')
  skip left:  'e' is alphanumeric → i stays 3
  skip right: ' ' is junk → j-- → j=5, 'a' is ok → stop
  compare: 'e' != 'a' → return false ✓
```

---

**Why `i < j` is inside the inner while too**
- Without it: input `",,"` (all junk) → `i` flies past `j` past end of string → crash
- The guard stops the skip from overshooting when pointers meet

**Why it's `!Character.isLetterOrDigit()` not a regex**
- `isLetterOrDigit(c)` returns true for a-z, A-Z, 0-9 — exactly "alphanumeric"
- No new string created, works directly on the original characters

---

**What I Thought vs What I Should Have Used**
- First thought: "prepare the data (clean string), then apply pattern"
- Should think: "apply the pattern, teach the pointer to handle the data"
- The skip is just a nested `while` that runs the pointer until it lands on a valid character
- This pattern repeats everywhere: skip spaces, skip duplicates, skip non-alphanumeric — same skeleton

**The Skip Pattern (memorise this):**
```
Skip spaces          →  while s[i] == ' '
Skip duplicates      →  while s[i] == s[i-1]
Skip non-alphanumeric → while !isLetterOrDigit(s[i])
```

**Edge Cases**
- `" "` (space only) → both pointers skip, `i<j` becomes false, return true ✓
- Empty string → outer while never runs, return true ✓
- All junk `",,"` → inner while guard `i<j` prevents crash ✓
- Single character → `i<j` is false immediately ✓

> **Revisit Needed:** Think SKIP not CLEAN when handling junk characters. Also remember regex `[^...]` = NOT. Trace the inner while guard on all-junk input.

**Asked In:** Facebook, Microsoft, Amazon (string + Two Pointer combo)

---

### [LC 344] Reverse String
**Status:** ✅ Done | 12-May-2026 | **Revisit: No**
**Pattern:** P4 — Two Pointers, opposite ends
**Difficulty:** Easy | [Problem](http://lcid.cc/344)

**Problem:** Reverse a char array in-place. O(1) extra memory.

**My Approach**
- Knew it was Two Pointers. `i=0, j=end`. Swap `s[i]` and `s[j]` using `temp`, move both inward until `i < j`.

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Why It Works (say this out loud!)**
- Reversing is symmetric — position 0 swaps with n-1, position 1 with n-2, and so on
- Two pointers do this in one pass without making a copy
- When pointers meet or cross, every element has been swapped exactly once

**What I Thought vs What I Should Have Used**
- Identified Two Pointers immediately ✅
- Weakness: wrote `//Swapping easily the two` — that's the WHAT, not the WHY
- Train the habit: always write WHY it works, not just what you did

**Bonus Trick — Swap Without `temp` (XOR swap)**
```java
s[i] ^= s[j];   // s[i] = s[i] XOR s[j]
s[j] ^= s[i];   // s[j] = original s[i]
s[i] ^= s[j];   // s[i] = original s[j]
```
- Eliminates the temp variable entirely
- Interviewers sometimes ask: *"can you swap without a third variable?"* — this is the answer
- `temp` is fine in practice; know this for follow-ups

**`i < j` not `i <= j`** — correct boundary this time ✅ (unlike LC167 where you had `j >= i`)

**Edge Cases**
- Single character — `i < j` is false immediately, no swap, correct
- Even vs odd length — both work, middle element in odd length never gets touched (correct)
- Empty array — loop never runs

**Asked In:** Amazon, Microsoft, Apple (classic warm-up)

---

### [LC 167] Two Sum II — Sorted Array
**Status:** ✅ Done | 12-May-2026 | **Revisit: Yes**
**Pattern:** P4 — Two Pointers, both ends
**Difficulty:** Medium | [Problem](http://lcid.cc/167)

**Problem:** Sorted 1-indexed array. Find two indices where `nums[i] + nums[j] == target`. One solution guaranteed.

**My Approach**
- i=0, j=end. Move based on sum: `sum > target → j--`, `sum < target → i++`, `sum == target → return {i+1, j+1}`

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Trick — the core idea**
- `i` is always at the SMALLEST, `j` always at the LARGEST → that pair is the boundary of all possible sums
- Boundary too big → `j` is useless for EVERYONE → kill it (`j--`)
- Boundary too small → `i` is useless for EVERYONE → kill it (`i++`)
- Every step = ELIMINATING with certainty, not guessing

**What I Thought vs What I Should Have Used**
- Needed hint → jumped to HashMap first (LC1 habit)
- Should have asked: *"array is sorted — what does that give me for free?"*
- Sorted = I can STEER. Two extremes = the boundary. That unlocks Two Pointers.
- **Lesson:** sorted array → Two Pointers BEFORE HashMap, every time

**Bug I Had (subtle!)**
- Used `while(j >= i)` — when `j==i`, both pointers on SAME element (illegal, problem says index1 < index2)
- Fix: `while(j > i)` — stops before they meet
- Didn't break here (solution exists before crossing) but would break in variants without guaranteed solution

**Edge Cases**
- Negative numbers — still works, sorted is sorted
- Answer at boundaries [0] and [n-1]
- 1-indexed output — don't forget `+1`!

> **Revisit Needed:** Needed hints. Next time: see sorted → think extremes → think steer. Practise explaining j>i vs j>=i out loud.

**Asked In:** Amazon, Google, Meta

---

### [LC 1] Two Sum — Unsorted
**Status:** ✅ Done | 13-Apr-2026 | **Revisit: No**
**Pattern:** P2 — HashMap
**Difficulty:** Easy | [Problem](http://lcid.cc/1)

**My Approach**
- `HashMap<value, index>`. For each num, check if `(target - num)` already in map. One pass.

**Optimal:** TIME: O(n) SPACE: O(n) ✅

**Trick**
- Store what you've SEEN (not what you need)
- `complement = target - current` → check map → if found, done; else add current
- Single pass works: when you reach B, A is already in the map

**What I Thought vs What I Should Have Used**
- Correctly jumped past brute force to HashMap ✅
- Key insight: "I need fast lookup of what I've seen" → HashMap

**Edge Cases**
- Duplicate values — works because you check BEFORE inserting
- `nums.length < 2`

> **Pattern Note:** LC167 (sorted) → P4 Two Pointers (O(1) space). LC1 (unsorted) → P2 HashMap (O(n) space). Same problem, sorted vs unsorted = completely different pattern!

---

### [LC 217] Contains Duplicates
**Status:** ✅ Done | 15-Apr-2026 | **Revisit: No**
**Pattern:** P2 — HashSet
**Difficulty:** Easy | [Problem](http://lcid.cc/217)

**My Approach**
- HashSet. If `set.add()` returns false → duplicate found.

**Optimal:** TIME: O(n) SPACE: O(n) ✅

**Trick**
- `set.add()` returns `false` if element already exists — use that boolean directly
- `contains() + add()` = two ops. `add()-and-check` = one op

**What I Thought vs What I Should Have Used**
- First tried sorting (P3) — correct logic but O(n log n)
- Then upgraded to HashSet O(n) ✅

**Edge Cases:** empty array, single element, all same

---

### [LC 219] Contains Duplicates II — within window k
**Status:** ✅ Done | 15-Apr-2026 | **Revisit: Yes**
**Pattern:** P5 Sliding Window + P2 HashSet inside window
**Difficulty:** Easy | [Problem](http://lcid.cc/219)

**My Approach**
- Sliding window of size k using HashSet. Add current, remove element that left: `nums[i-k]`

**Optimal:** TIME: O(n) SPACE: O(k) ✅

**Trick**
- Fixed window max size = k
- When window grows beyond k, evict oldest: `nums[i - k]`
- Duplicate inside window = indices are ≤ k apart

**What I Thought vs What I Should Have Used**
- Correctly identified sliding window ✅
- Signal to remember: "within distance k" → sliding window of size k

**Edge Cases:** k=0 (always false), k ≥ n, all same elements

> **Revisit Needed:** Remember WHY we remove `nums[i-k]` not `nums[i-k-1]`. Window is `[i-k+1 .. i]`, size = k. When i=k, remove index 0 = `nums[i-k]`.

---

### [LC 169] Majority Element
**Status:** ✅ Done | 13-Apr-2026 | **Revisit: Yes**
**Pattern:** P7 — Boyer-Moore Voting
**Difficulty:** Easy | [Problem](http://lcid.cc/169)

**My Approach**
- Sort → count consecutive. Also tried HashMap with `getOrDefault`.

**Optimal:** TIME: O(n) SPACE: O(1) — Boyer-Moore

**Trick — Boyer-Moore**
```
candidate = nums[0], count = 1
for each num:
  if count == 0  → candidate = num, count = 1
  else if num == candidate → count++
  else → count--
return candidate
```
- Every cancel consumes one majority vote AND one minority vote
- Majority (> n/2) has more than all others combined → always survives

**What I Thought vs What I Should Have Used**
- Used sort O(n log n) and HashMap O(n) space
- Boyer-Moore: O(1) space, O(n) time
- Signal: "majority ALWAYS exists" + "appears > n/2" → Boyer-Moore

**Edge Cases:** single element, all same, alternating

> **Revisit Needed:** Code Boyer-Moore from memory. Trace [2,2,1,1,1,2,2] by hand.

**Asked In:** Amazon, Adobe, Uber

---

### [LC 283] Move Zeroes
**Status:** ✅ Done | 15-Apr-2026 | **Revisit: No**
**Pattern:** P4 — Two Pointers (slow writer + fast reader)
**Difficulty:** Easy | [Problem](http://lcid.cc/283)

**My Approach**
- Write pointer `k`. Copy non-zeros forward, fill tail with 0s.

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Trick — write pointer**
- `slow = k` → next position to write a non-zero
- `fast = i` → scans whole array
- After loop: zero out `k..n-1`

**What I Thought vs What I Should Have Used**
- Nailed two-pass version ✅
- One-pass swap also exists: `if (nums[i] != 0) swap(nums[i], nums[k++])` — fewer writes when zeros are rare

**Edge Cases:** all zeros, no zeros, single element, zeros already at end

---

### [LC 268] Missing Number
**Status:** ✅ Done | 18-Apr-2026 | **Revisit: Yes**
**Pattern:** P8 Math (Gauss) or P9 XOR
**Difficulty:** Easy | [Problem](http://lcid.cc/268)

**My Approach**
- HashSet first (O(n) space), then optimised to Gauss formula.

**Optimal:** TIME: O(n) SPACE: O(1)

**Trick — Gauss**
- `expected = n*(n+1)/2` → `missing = expected - sum(nums)`

**Trick — XOR**
- XOR all indices `0..n` XOR all nums → pairs cancel → missing remains

**What I Thought vs What I Should Have Used**
- HashSet → Gauss progression is the right learning path ✅
- XOR is the show-off answer — no overflow risk

**Edge Cases:** missing 0, missing n, single element

> **Revisit Needed:** Know both Gauss AND XOR. Be able to explain either to an interviewer.

---

### [LC 448] Find All Disappeared Numbers
**Status:** ✅ Done | 18-Apr-2026 | **Revisit: No**
**Pattern:** P10 — In-Place Index Marking
**Difficulty:** Easy | [Problem](http://lcid.cc/448)

**My Approach**
- HashSet (O(n) space), boolean[] (O(n) space).

**Optimal:** TIME: O(n) SPACE: O(1) — In-Place Negation

**Trick — In-Place Negation**
- Values in [1..n], indices in [0..n-1]
- For each num, negate `nums[abs(num) - 1]`
- After marking: index `i` where `nums[i] > 0` → `i+1` was never seen

**What I Thought vs What I Should Have Used**
- Used HashSet and boolean[] — correct but O(n) space
- Signal: "values in [1..n], array size n" → use array itself as hash, mark by negating

> **Revisit Needed:** In-place negation is not obvious. Code it from scratch. Also appears in LC442.

**Asked In:** Facebook, Google

---

### [LC 136] Single Number
**Status:** ✅ Done | 18-Apr-2026 | **Revisit: Yes**
**Pattern:** P9 — XOR Bit Manipulation
**Difficulty:** Easy | [Problem](http://lcid.cc/136)

**My Approach**
- XOR all elements — pairs cancel (`a^a=0`), lone element remains (`a^0=a`).

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Trick**
- `a ^ a = 0` and `a ^ 0 = a`
- XOR is commutative + associative → order doesn't matter
- All pairs annihilate, loner survives

**What I Thought vs What I Should Have Used**
- Went straight to XOR ✅ (HashSet is the common wrong path — O(n) space)

**Edge Cases:** single element, negative numbers (XOR still works — just bit patterns)

> **Revisit Needed:** Correct solution but practise explaining the XOR property out loud without notes.

**Asked In:** Amazon, Microsoft, Adobe

---

### [LC 485] Max Consecutive Ones
**Status:** ✅ Done | 18-Apr-2026 | **Revisit: Yes**
**Pattern:** P4 — Simple counter (single pass)
**Difficulty:** Easy | [Problem](http://lcid.cc/485)

**My Approach**
- Two variables K (current streak) and T (max seen). On zero: compare K vs T, swap if bigger, reset K.

**Optimal:** TIME: O(n) SPACE: O(1)

**Trick**
- On every 0: `max = Math.max(max, current); current = 0`
- **CRITICAL:** after loop ends → `max = Math.max(max, current)` to catch streak that runs to end

**What I Thought vs What I Should Have Used**
- K/T swap logic is correct but messier than `Math.max()`
- #1 bug: forgetting the post-loop check — `[1,1,1]` hits no zero so max never updates inside loop!

**Edge Cases**
- All 1s → no zero hit → **must check after loop**
- All 0s → max stays 0
- Single element

> **Revisit Needed:** Trace [1,1,1] with your code — does it return 3 without the post-loop check?

**Asked In:** Facebook, Amazon

---

### [LC 26] Remove Duplicates from Sorted Array
**Status:** ✅ Done | 13-Apr-2026 | **Revisit: Yes**
**Pattern:** P4 — Two Pointers (slow writer k, fast reader i)
**Difficulty:** Easy | [Problem](http://lcid.cc/26)

**My Approach**
- Loop, check next element equals current. If different, swap/write to next slot.

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Trick — write pointer**
- `k` = next position to write a unique value
- `if (nums[i] != nums[k-1]) → nums[k++] = nums[i]`
- No swapping — just overwrite forward

**What I Thought vs What I Should Have Used**
- Used swap (extra moves) instead of write-pointer (one move)
- Compare with LC283 — identical skeleton, only keep-condition differs

**Edge Cases:** empty, all duplicates `[1,1,1] → k=1`, no duplicates, single element

> **Revisit Needed:** Write the two-pointer version WITHOUT swap. Template: `k=1; for i=1..n: if nums[i]!=nums[k-1] → nums[k++]=nums[i]`

**Asked In:** Microsoft, Apple

---

### [LC 27] Remove Element
**Status:** ✅ Done | 13-Apr-2026 | **Revisit: No**
**Pattern:** P4 — Two Pointers (write pointer)
**Difficulty:** Easy | [Problem](http://lcid.cc/27)

**My Approach**
- Write pointer K. If element `!= val`, write it to K and increment K.

**Optimal:** TIME: O(n) SPACE: O(1) ✅

**Trick**
- Same skeleton as LC26 and LC283. Keep condition: `nums[i] != val`

**The Write-Pointer Family (memorise this):**

| Problem | Keep condition |
|---|---|
| LC27 Remove Element | `nums[i] != val` |
| LC26 Remove Dups | `nums[i] != nums[k-1]` |
| LC283 Move Zeroes | `nums[i] != 0` |

**Edge Cases:** val not present (k=n), all elements equal val (k=0), empty

---

### [LC 66] Plus One
**Status:** ✅ Done | 13-Apr-2026 | **Revisit: No**
**Pattern:** P8 — Math / Carry Propagation
**Difficulty:** Easy | [Problem](http://lcid.cc/66)

**My Approach**
- Reverse loop. Digit == 9 → set to 0, continue. First non-9 → increment and return. All 9s → prepend 1.

**Optimal:** TIME: O(n) SPACE: O(1) amortized ✅

**Trick**
- Traverse from RIGHT (least significant digit)
- Any digit < 9 → increment and STOP immediately (early exit)
- All-9s input → `new int[n+1]` with `result[0] = 1` (Java zero-inits the rest)

**Edge Cases:** `[9] → [1,0]`, `[9,9,9] → [1,0,0,0]`, `[1,9] → [2,0]`

---

## Section 3 — Pattern Templates (Java)

### P4 — Two Pointers Template A: Opposite Ends (sorted array)
```java
int left = 0, right = n - 1;
while (left < right) {
    int sum = nums[left] + nums[right];
    if      (sum < target) left++;
    else if (sum > target) right--;
    else /* found */ break;
}
```

### P4 — Two Pointers Template B: Write Pointer (in-place filter)
```java
int write = 0;
for (int read = 0; read < n; read++) {
    if (KEEP_CONDITION) {
        nums[write++] = nums[read];
    }
}
```

### P5 — Sliding Window (fixed size k)
```java
Set<Integer> window = new HashSet<>();
for (int i = 0; i < n; i++) {
    if (window.contains(nums[i])) return true;
    window.add(nums[i]);
    if (window.size() > k) window.remove(nums[i - k]);
}
```

### P7 — Boyer-Moore Voting
```java
int candidate = nums[0], count = 1;
for (int i = 1; i < n; i++) {
    if (count == 0) { candidate = nums[i]; count = 1; }
    else if (nums[i] == candidate) count++;
    else count--;
}
return candidate;
```

### P10 — In-Place Negation
```java
// Pass 1: mark
for (int i = 0; i < n; i++) {
    int idx = Math.abs(nums[i]) - 1;   // map value → index
    if (nums[idx] > 0) nums[idx] = -nums[idx];
}
// Pass 2: collect — index i where nums[i] > 0 → (i+1) is missing
```

---

## Section 4 — My Personal Mistake Log

| ❌ Mistake | ✅ Fix |
|---|---|
| Defaulting to HashMap for everything | Ask "is it sorted?" first → sorted → Two Pointers |
| Forgetting 1-indexed output | Read output spec → if 1-indexed, return `i+1` |
| Using sort when O(n) is enough | Sorting = O(n log n). Can you do it in O(n)? |
| Missing "values in [1..n]" signal | That + size n → always think P10 In-Place Negation |
| `j >= i` instead of `j > i` | `j > i` stops before same element — use this always |
| Forgetting post-loop max check | If streak can run to end of array, check AFTER loop too |
| No null/empty guard | First line: `if (nums == null \|\| nums.length == 0) return ...` |

---

## Section 5 — Next Problems (by pattern)

| Status | Problem | Date | Notes |
|---|---|---|---|
| ✅ | LC27 Remove Element | 13-Apr-2026 | — |
| ✅ | LC26 Remove Dups (Sorted) | 13-Apr-2026 | Revisit: swap→write |
| ✅ | LC283 Move Zeroes | 15-Apr-2026 | — |
| ✅ | LC167 Two Sum II | 12-May-2026 | Revisit: sorted→extremes→steer |
| ✅ | LC344 Reverse String | 12-May-2026 | No revisit — write WHY not just WHAT |
| ✅ | LC125 Valid Palindrome | 12-May-2026 | Revisit: think SKIP not CLEAN. Inner while guard. |
| ✅ | LC392 Is Subsequence | 13-May-2026 | Revisit: label pointers before coding. Know binary search follow-up. |
| ⬜ | LC15 3Sum | — | Sort + Two Pointers (harder) |
| ✅ | LC219 Contains Dup II | 15-Apr-2026 | Revisit: window eviction |
| ⬜ | LC643 Max Average Subarray I | — | Fixed sliding window |
| ⬜ | LC3 Longest Substr No Repeat | — | Variable sliding window |
| ⬜ | LC303 Range Sum Query | — | Prefix Sum |
| ⬜ | LC560 Subarray Sum Equals K | — | Prefix Sum |
| ✅ | LC169 Majority Element | 13-Apr-2026 | Revisit: code Boyer-Moore from memory |
| ✅ | LC136 Single Number | 18-Apr-2026 | Revisit: explain XOR out loud |
| ✅ | LC268 Missing Number | 18-Apr-2026 | Revisit: know XOR version too |
| ✅ | LC448 Find Disappeared Nums | 18-Apr-2026 | — |
| ⬜ | LC442 Find All Duplicates | — | In-Place Negation |
| ✅ | LC66 Plus One | 13-Apr-2026 | — |
| ✅ | LC1 Two Sum | 13-Apr-2026 | — |
| ✅ | LC217 Contains Duplicates | 15-Apr-2026 | — |
| ✅ | LC485 Max Consecutive Ones | 18-Apr-2026 | Revisit: post-loop check |

---

## Section 6 — Interview Prep

> Full Q&A, follow-ups, and company notes → **`Array_Interview.md`**

---

*Last updated: 2026-05-13 | Problems logged: 17 | Next: LC15 3Sum*
*Companion files: `General_HolyBook.md` · `Array_Interview.md`*
