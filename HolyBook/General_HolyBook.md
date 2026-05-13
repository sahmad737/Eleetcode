# GENERAL — HOLY BOOK (Sameer's DSA Diary)

> **This file has NO topic. It's the meta-layer above everything else.**
> Read this FIRST before opening any topic HolyBook.
> Come back here when you're stuck on HOW to think, not what to code.

**Companion files (topic-specific):**
- `Array_HolyBook.md` — array patterns, problems, templates
- `Array_Interview.md` — array Q&A, follow-ups, company notes
- *(LinkedList_HolyBook.md — coming soon)*

---

## Section 1 — How to Approach ANY DSA Problem

Every problem — easy or hard — follows the same thinking loop. Don't skip steps. Silence at the wrong moment looks bad in interviews.

| Step | What to do | Why |
|---|---|---|
| **1. Read & Repeat Back** | Restate the problem in your own words out loud | Catches misunderstandings before you waste time coding |
| **2. Read Constraints** | n size → time ceiling. Value range → tricks. Keywords → pattern | See Section 2 — the 3-question habit |
| **3. Brute Force First** | Say it out loud, don't code it: *"Naive way is O(n²) because…"* | Shows understanding. Sets the baseline. |
| **4. Ask What You're Wasting** | *"What am I recomputing? What info am I throwing away?"* | Recomputing sums → Prefix Sum. Membership checks → HashSet. Sorted + searching → Two Pointers |
| **5. Trace by Hand** | Pick 4-5 elements, walk through logic step by step | If you can't trace it, you can't code it |
| **6. Code** | Only now. Keep talking while you type | — |
| **7. Edge Cases Out Loud** | Empty? Single element? All same? All zeros? Max n? | Say them before the interviewer asks |
| **8. State Complexity** | *"Time is O(n) because one pass. Space is O(1) — only two variables."* | Never wait to be asked |

---

## Section 2 — How to Read Constraints

> Do this at Step 2, every time, before writing a single line.

### n size → Time Complexity Ceiling

| n constraint | Max ops allowed | Solution needed |
|---|---|---|
| n ≤ 10 | any | Brute force / recursion fine |
| n ≤ 1,000 | ~1M | O(n²) fine |
| n ≤ 100,000 | ~17M | O(n log n) max |
| n ≤ 1,000,000 | ~20M | O(n) required — no nested loops |
| n ≤ 10^9 | can't loop | O(log n) or O(1) math only |

### Value Ranges → What Tricks Are Available

| Constraint | What it unlocks |
|---|---|
| values in [1..n], size = n | **In-Place Negation** — mark without O(n) space |
| values in [0..1] | Count 0s and 1s — simple partition |
| values can be negative | XOR & Two Pointers still work |
| values are distinct | No duplicate logic needed |
| values are non-negative | Prefix sum safe — no sign flip issues |

### Keyword → Pattern Signal

| You see this | Think this first |
|---|---|
| "sorted" / "non-decreasing" | **Two Pointers** before HashMap |
| "within distance k" | **Sliding Window** of size k |
| "subarray sum" / "range query" | **Prefix Sum** |
| "appears more than n/2 times" | **Boyer-Moore Voting** |
| "1-indexed output" | Add +1 to your return — **don't forget!** |
| "in-place" / "O(1) extra space" | No HashSet/array — ~5 variables max |
| "exactly one solution" | No "not found" case needed |
| "maximum / minimum subarray" | Kadane's or Sliding Window |
| "top k" / "kth largest" | Heap / QuickSelect |
| "binary search on answer" | When the answer range is monotonic |

### The 3-Question Habit

Ask yourself before writing **anything**:

1. **How big is n?** → sets your time complexity ceiling
2. **What are the value ranges?** → opens/closes XOR, In-Place, Gauss, Prefix Sum
3. **Any free gifts?** → sorted? distinct? in [1..n]? → maps to a pattern

> Most bugs and TLE come from skipping these 3 questions.

---

## Section 3 — O(?) — How to Think About Complexity

### What O(1) Space Actually Means

O(1) space = extra memory you create does **NOT** grow with input size.
The input array/string itself is NOT counted — you're given that.

```
✅ O(1) — int left=0, right=n-1, temp=0
           → 3 variables always, doesn't matter if n=5 or n=1,000,000

❌ O(n)  — new HashSet<>()
           → if n=1M, set grows to 1M entries

❌ O(n)  — new int[nums.length]
           → copy scales with input
```

**Quick rule:** *"Can I solve this with only ~5 integer variables?"*
Yes → O(1) space is possible. No → you need O(n) space.

### Complexity Cheat Sheet

| Operation | Time | Space | Notes |
|---|---|---|---|
| Array access `nums[i]` | O(1) | — | Random access, instant |
| HashSet add/contains | O(1)* | O(n) | *amortized |
| HashMap get/put | O(1)* | O(n) | *amortized |
| `Arrays.sort()` | O(n log n) | O(log n) | In-place Timsort |
| Binary Search | O(log n) | O(1) | Sorted input only |
| Single loop | O(n) | O(1) | — |
| Nested loop (2 levels) | O(n²) | O(1) | Too slow when n > 1,000 |
| Recursion depth d | — | O(d) | Each call = stack frame |

### How to Calculate Time Complexity

**Step 1 — Count the loops:**
```
One loop over n           → O(n)
Loop inside loop over n   → O(n²)
Loop that halves each step → O(log n)
Loop over n, inside log n → O(n log n)
```

**Step 2 — Drop constants and lower terms:**
```
O(2n + 5)  →  O(n)
O(n² + n)  →  O(n²)   ← n² dominates
```

**Step 3 — For space, ask: "What grows with n?"**
- Fixed number of variables → O(1)
- Collection that fills based on input → O(n)

---

### The Space-Time Tradeoff — When to Sacrifice Which

**Rule 1 — Read the problem constraints first**

| Constraint in problem | What to sacrifice |
|---|---|
| "Do it in-place" / "O(1) extra space" | Must sacrifice time if needed |
| "O(n) time" / no space restriction | Pay O(n) space freely |
| No constraint mentioned | Default: optimize time, pay space |

**Rule 2 — The "must return new structure" exception**

If the problem *forces* you to return a new array or list, that output is O(n) no matter what.
You are already paying O(n) space just for the answer — the extra space isn't a cost, it's mandatory.
Use it: store intermediate results in the output array and avoid a re-sort or second pass.

> Example: LC977 Squares of Sorted Array — result[] is required output, not extra.
> Paying O(n) for it gets you O(n) time. Not paying = O(n log n) for re-sort. Wrong tradeoff.

**Rule 3 — Decision flowchart**

```
Is O(1) space required? ("in-place", "O(1) extra")
  YES → accept worse time; use index tricks, pointer tricks
  NO  → ask: does paying O(n) space buy me better time?
          YES → pay it
          NO  → save space, no benefit to spending it
```

**Rule 4 — Common space-for-time trades**

| Pay this space | To get this time gain | Example |
|---|---|---|
| O(n) HashMap | O(n²) → O(n) lookup | Two Sum LC1 |
| O(n) result array | O(n log n) → O(n) | Squares LC977 |
| O(n) prefix sum array | O(n) build → O(1) per range query | LC303, LC560 |
| O(k) HashSet window | O(n²) → O(n) sliding window | LC219 |

**Rule 5 — Default stance for interviews**

Unless the problem says otherwise: **optimize time first, explain the space cost**.
Then offer: *"If space is a concern, I can do it in O(1) space at the cost of O(n log n) time."*
Showing you know both directions impresses interviewers more than picking one silently.

---

## Section 4 — Data Structures: When to Use What

| Structure | Use when |
|---|---|
| `int[]` / Array | Fast index access, fixed size, in-place tricks |
| `ArrayList` | Need dynamic size, ordered, allow duplicates |
| `HashSet` | "Has this been seen?" — O(1) lookup, no order |
| `HashMap` | "What's associated with this key?" — O(1) lookup |
| `Stack` | Last-in-first-out — brackets, undo, DFS |
| `Queue` | First-in-first-out — BFS, level order |
| `Deque` | Both ends access — sliding window max/min |
| `PriorityQueue` (Heap) | "Top k", "kth largest" — O(log n) insert/remove |
| `TreeMap` / `TreeSet` | Sorted map/set — range queries, floor/ceiling |

**Decision flowchart:**
```
Need fast lookup?            → HashMap / HashSet
Need sorted order + lookup?  → TreeMap / TreeSet
Need min or max repeatedly?  → PriorityQueue
Need both ends?              → Deque
Need LIFO?                   → Stack
Fixed size, index access?    → Array
```

---

## Section 5 — General Mistake Log

Add entries here every time you make a recurring mistake across topics.

| ❌ Mistake | ✅ Fix |
|---|---|
| Jumping to code without reading constraints | Force the 3-question habit first |
| Defaulting to brute force without checking if input is sorted | *"Is it sorted?"* → yes → Two Pointers replaces inner loop |
| Forgetting 1-indexed output | Read output spec. If 1-indexed → return `i+1` |
| `j >= i` instead of `j > i` in Two Pointer | `j > i` stops before same element — use this always |
| Forgetting post-loop max check for streaks | If streak can run to end of array, check AFTER loop too |
| O(n) space when problem says in-place | "in-place" → only ~5 variables — no HashSet or extra array |
| No null/empty input guard | First line: `if (nums == null \|\| nums.length == 0) return ...` |
| Solving the wrong problem (misread) | Restate in your own words BEFORE coding |

---

## Section 6 — Interview Meta: What to Say and When

### When You're Stuck

Don't go silent. Say one of these:
- *"Let me think about what the brute force would look like first."*
- *"The n is large so I need O(n) — let me think what tricks apply."*
- *"I know HashMap gives O(1) lookup — does that help here?"*
- *"The array is sorted — maybe I can steer with two pointers."*

### After You Code

Always say **without being asked**:
- *"Time complexity is O(?) because …"*
- *"Space complexity is O(?) because …"*
- *"Edge cases I'd test: empty input, single element, all same, overflow."*

### Follow-Up Questions (be ready for these after any solution)

- *"Can you do it in O(1) space?"*
- *"What if the array doesn't fit in memory?"*
- *"What if there are duplicates?"*
- *"What if no solution is guaranteed — how does your code change?"*
- *"Can you do it in a single pass?"*

### The Confidence Trick

> Interviewers reward thinking out loud more than perfect code.
> A clean, narrated O(n²) solution > a silent, broken O(n) attempt.
> **Code second. Think first. Talk always.**

---

## Section 7 — Revision Priority

### Day Before Interview
- [ ] Read your personal Mistake Log (Section 5 here + Section 4 in Array_HolyBook)
- [ ] Trace through 1 "Revisit: Yes" problem from each topic without notes
- [ ] Recite Boyer-Moore, XOR trick, In-Place Negation out loud

### 1 Hour Before
- [ ] Re-read the Pattern Glossary (`Array_HolyBook.md` Section 1)
- [ ] Re-read this Constraint cheat sheet (Section 2 above)
- [ ] Re-read Interview Meta (Section 6 above)

### During Interview
- [ ] Read constraints before coding
- [ ] Say brute force first
- [ ] Trace example by hand
- [ ] State complexity without being asked
- [ ] Mention edge cases

---

*Last updated: 2026-05-14*
*Files: `General_HolyBook.md` · `Array_HolyBook.md` · `Array_Interview.md`*
