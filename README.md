# DSA-Java

My personal Java practice repo for data structures, algorithms, and LeetCode-style problems.

## Structure & Naming Convention

Every topic gets its own folder. Inside each folder:
- **`<DataStructure>.java`** — from-scratch implementation
- **`<Topic>Problems.java`** or **`MediumQue.java` / `HardQue.java`** — problems solved using that structure

---

## Folder Index

| Folder | Files | Contents |
|---|---|---|
| `Arrays/` | `ArrayBasics`, `ArrayProblems`, `Array2DProblems`, `TwoSum`, `LargestElementDemo` | Array basics, 2D arrays, two sum |
| `Strings/` | `StringBasics`, `StringProblems` | String manipulation, common problems |
| `LinkedList/` | `SinglyLinkedList`, `DoublyLinkedList`, `CircularLinkedList`, `ListNode`, `LinkedListProblems` | All linked list types + problems |
| `Stack/` | `StackImplementation`, `StackProblems`, `MonotonicStack`, `StackVariants` | Stack impl, problems, monotonic stack |
| `Queue/` | `QueueImplementation`, `CircularQueue`, `QueueProblems` | Queue impl, circular queue, problems |
| `Heap/` | `Heap`, `MediumQue`, `HardQue` | Min/max heap, heap sort, medium & hard problems |
| `HashMap/` | `HashMap`, `HashMapTypes`, `HashSetTypes`, `MapQuestions`, `SetQuestions` | Custom HashMap impl, map/set types, problems |
| `Trees/` | `BinaryTree`, `BST`, `AVLTree`, `TreeProblems` | Binary tree, BST, AVL tree, tree problems |
| `Trie/` | `Trie`, `EasyQuestions`, `HardQuestions` | Trie impl, prefix/search problems, word break |
| `Graph/` | `Graph`, `BFS`, `DFS`, `Dijkstra`, `GraphProblems` | Graph representations, traversals, shortest path |
| `Recursion/` | `Recursion` | Recursion fundamentals |
| `Backtracking/` | `backtracking` | Subsets, permutations, N-Queens, Sudoku, rat in maze, keypad, word break |
| `Searching/` | `LinearSearch`, `BinarySearch`, `SearchProblems` | Linear & binary search + problems |
| `Sorting/` | `BubbleSort`, `MergeSort`, `QuickSort`, `HeapSort`, `SortingExtras` | All major sorting algorithms |
| `DynamicProgramming/` | `DPBasics`, `Knapsack`, `DPProblems` | DP fundamentals, knapsack, classic problems |
| `Greedy/` | `GreedyProblems` | Greedy algorithm problems |
| `BitManipulation/` | `BitManipulation` | Bit tricks and problems |
| `Patterns/` | `SlidingWindow`, `TwoPointers`, `PrefixSum`, `DifferenceArray`, `FastSlowPointer`, `MergeIntervals`, `TopKElements` | Reusable problem-solving templates |
| `PatternPrinting/` | `Star`, `Triangle`, `Rhombus`, `Butterfly`, `FloydTriangle`, ... | Star/pyramid/pattern printing |

---

## HashMap Folder Detail

| File | Contents |
|---|---|
| `HashMap.java` | Custom HashMap from scratch (chaining, resize, put/get/remove) |
| `HashMapTypes.java` | HashMap vs LinkedHashMap vs TreeMap with demos |
| `HashSetTypes.java` | HashSet vs LinkedHashSet vs TreeSet with demos |
| `MapQuestions.java` | majorityElement, isAnagram, findItinerary, largestSubarraySumZero, subarraySumK |
| `SetQuestions.java` | union, intersection, countDistinct, countDistinctInWindows |

---

## Heap Folder Detail

| File | Contents |
|---|---|
| `Heap.java` | Min-heap impl: insert, remove, heapify, heapSort |
| `MediumQue.java` | kNearestCars, connectRopes, kWeakestRows, slidingWindowMax, kthLargestStream |
| `HardQue.java` | minTimeFillSlots, minimumEffortPath, minOperationsToHalve, mergeKLists |

---

## Trie Folder Detail

| File | Contents |
|---|---|
| `Trie.java` | Trie impl: insert, search, startsWith |
| `EasyQuestions.java` | prefixCount, wordsWithPrefix, countUniqueStrings, groupAnagrams |
| `HardQuestions.java` | longestWordInDictionary, longestWordWithAllPrefixes, wordBreak I & II |

---

## BST Methods (inside `Trees/BST.java`)

insert, search, delete, printInRange, rootToLeafPaths, isValidBST, mirror, sortedArrayToBST, balanceBST, largestBST, mergeBSTs, rangeSum, closestElement, kthSmallest, maxSumBST
