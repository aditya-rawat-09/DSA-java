# DSA-Java

My personal Java practice repo for data structures, algorithms, and LeetCode-style problems.

## Structure & naming convention

Every topic gets its own lowercase package (`arrays`, `linkedlist`, `trees`, ...). Inside each folder:

- **`<DataStructure>.java`** — a from-scratch implementation (e.g. `SinglyLinkedList.java`, `BST.java`, `HeapImplementation.java`).
- **`<Topic>Problems.java`** — LeetCode-style problems solved using that structure, with `// Q.N` / `// ─── NAME ───` headers above each solution.
- A few topics (Sorting, Backtracking, Searching) get one file per algorithm instead, since each is short and self-contained.

This mirrors what was already a strong instinct in the original repo (e.g. `Trie.java` vs `EasyQuestions.java`/`HardQuestions.java`) — just applied consistently everywhere.

## Folder index

| Folder | Contents |
|---|---|
| `Arrays/` | `ArrayBasics`, `ArrayProblems`, `Array2DProblems`, `TwoSum`, `LargestElementDemo` |
| `Strings/` | `StringBasics`, `StringProblems` |
| `LinkedList/` | `ListNode`, `SinglyLinkedList`, `DoublyLinkedList`, `CircularLinkedList`, `LinkedListProblems` |
| `Stack/` | `StackImplementation`, `StackProblems`, `MonotonicStack`, `StackVariants` (bonus: ArrayList- and LinkedList-backed) |
| `Queue/` | `QueueImplementation`, `CircularQueue`, `QueueProblems` |
| `Heap/` | `HeapImplementation`, `HeapProblems`, `PriorityQueueProblems` |
| `Hashing/` | `HashMapExamples`, `HashSetExamples`, `HashingProblems`, `CustomHashMap` (bonus: from-scratch HashMap) |
| `Trees/` | `BinaryTree`, `BST`, `AVLTree`, `TreeProblems` (Advanced / Subtree / Top View, as nested classes) |
| `Graph/` | `BFS`, `DFS`, `Dijkstra`, `GraphProblems` |
| `Trie/` | `Trie`, `TrieProblems` |
| `Recursion/` | `Recursion` |
| `Backtracking/` | `NQueens`, `SudokuSolver`, `BacktrackingProblems` |
| `Searching/` | `LinearSearch`, `BinarySearch`, `SearchProblems` |
| `Sorting/` | `BubbleSort`, `MergeSort`, `QuickSort`, `HeapSort`, `SortingExtras` (selection/insertion/counting/cyclic) |
| `BitManipulation/` | `BitManipulation` |
| `DynamicProgramming/` | `DPBasics`, `Knapsack`, `DPProblems` |
| `Greedy/` | `GreedyProblems` |
| `Patterns/` | Reusable problem-solving templates: `SlidingWindow`, `TwoPointers`, `PrefixSum`, `DifferenceArray`, `FastSlowPointer`, `MergeIntervals`, `TopKElements` |
| `PatternPrinting/` | Star/pyramid/rhombus printing exercises |

## TODO files

A few files are placeholders (marked with a `// TODO:` comment) for structure slots that didn't exist yet in the original code — e.g. `AVLTree.java`, `Dijkstra.java`, `Knapsack.java`, `CircularQueue.java`, `HeapSort.java`, `LinearSearch.java`, `GreedyProblems.java`. Fill these in as you cover those topics; the layout is ready for them.

## What changed from the old layout

- All folders renamed to lowercase package names (`array` → `arrays`, `bitmanuplation` → `BitManipulation`, etc.) and every file now has a matching `package ...;` declaration.
- Implementation vs. problem-set split applied consistently (was inconsistent before — `Heap`/`Trie` already did this, `array`/`Stack` mixed things directly).
- Naming collisions fixed: the old `HashMap.java` class (which shadowed `java.util.HashMap`) is now `CustomHashMap`. `Queue.java`/`Stack.java`/etc. (which shadowed `java.util` types) renamed to `QueueImplementation`/`StackImplementation`.
- `pattern_printing/program.java` (an array exercise, not a pattern) moved to `Arrays/LargestElementDemo.java`.
- `pattern/*.java` template files merged into the matching topic folders where there was overlap (e.g. `twosum.java` → `Arrays/TwoSum.java`, `binarysearch.java` → `Searching/BinarySearch.java`) and kept in `Patterns/` where they're genuinely reusable templates.
