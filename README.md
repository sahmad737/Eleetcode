# Sameer's DSA Learning System

## How This Repo Works

```
Eleetcode/
├── Array/              ← Java solution files
├── HolyBook/           ← Pattern reference & brain dump  ← READ BEFORE SOLVING
│   └── Array_HolyBook.java
└── README.md
```

---

## Workflow For Every New Problem

```
1. READ   → Understand the problem. Identify: sorted? range [1..n]? window?
2. THINK  → Open Array_HolyBook.java. Match to a pattern in Section 1.
3. CODE   → Implement. Don't look at solutions yet.
4. LOG    → Add a block to the HolyBook with your tags (see below).
5. REVIEW → If you struggled, mark //Revisit Needed -- REASON.
```

---

## Comment Tags (use in EVERY solution file)

| Tag | When to use |
|-----|-------------|
| `//My Approach` | What you first coded |
| `//Optimal` | Best known: TIME O(?) SPACE O(?) |
| `//Pattern` | Pattern name from HolyBook Section 1 |
| `//Trick` | The single key insight |
| `//Revisit Needed -- REASON` | You got stuck or used the wrong pattern |
| `//What I Thought vs What I Should Have Used` | Pattern mismatch log |
| `//Edge Cases` | Inputs that can break your code |
| `//Asked In` | Company / round (fill from LC discussions) |

---

## Your Current Progress Snapshot (2026-05-12)

| Problem | Status | Pattern Used | Optimal Pattern |
|---------|--------|-------------|----------------|
| LC1  Two Sum | ✅ Optimal | HashMap | HashMap |
| LC167 Two Sum II | ⬜ Unsolved | — | **Two Pointers** ← DO THIS NEXT |
| LC217 Contains Duplicates | ✅ Optimal | HashSet | HashSet |
| LC219 Contains Duplicates II | ✅ Optimal | Sliding Window | Sliding Window |
| LC169 Majority Element | ⚠️ Suboptimal | Sort / HashMap | **Boyer-Moore** |
| LC283 Move Zeroes | ✅ Optimal | Write Pointer | Write Pointer |
| LC268 Missing Number | ✅ Optimal | Gauss Formula | Gauss / XOR |
| LC448 Find Disappeared Numbers | ⚠️ Suboptimal | HashSet / boolean[] | **In-Place Negation** |
| LC136 Single Number | 📝 Log needed | — | XOR |
| LC485 Max Consecutive Ones | 📝 Log needed | — | Counter |
| LC26 Remove Dups Sorted | 📝 Log needed | — | Two Pointers |
| LC27 Remove Element | 📝 Log needed | — | Write Pointer |
| LC66 Plus One | 📝 Log needed | — | Carry Propagation |

---

## Honest Assessment

**Strong at:** HashMap / HashSet — you reach for the right O(n) tool fast.

**Gap:** You default to HashSet even when O(1) space is possible.
Rule: **sorted array → Two Pointers first. Range [1..n] values → In-Place Negation.**

**Next milestone:** Solve LC167 with Two Pointers (no HashMap). Then re-solve LC169 with Boyer-Moore (no sort).

---

## Topics Roadmap

- [x] `Array_HolyBook.java` — started
- [ ] `LinkedList_HolyBook.java` — next after arrays
- [ ] `String_HolyBook.java`
- [ ] `BinarySearch_HolyBook.java`
- [ ] `Stack_Queue_HolyBook.java`
- [ ] `Tree_HolyBook.java`
- [ ] `Graph_HolyBook.java`
- [ ] `DP_HolyBook.java`
