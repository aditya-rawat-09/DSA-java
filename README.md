# DSA-Java

My personal Java practice repo for data structures, algorithms, and LeetCode-style problems.

---

## Folder Structure

```
DSA-Java/
├── Arrays/
├── BitManipulation/
├── DynamicProgramming/
├── Graph/
├── Greedy/
├── HashMap/
├── Heap/
├── LinkedList/
├── PatternPrinting/
├── Patterns/
├── Queue/
├── Recursion/
├── Searching/
├── Sorting/
├── Stack/
├── Strings/
├── Trie/
└── Backtracking/
```

---

## Arrays/

| File | Description |
|---|---|
| `ArrayBasics.java` | traverse, insertAt, deleteAt, reverse, rotateLeft, max, min, linearSearch, isSorted, secondLargest |
| `ArrayProblems.java` | twoSum, threeSum, fourSum, Kadane's maxSubarraySum, Dutch national flag, buy/sell stock I & II, kthLargestOdd, moveZeroes, findDuplicate, maxProduct, maxWater, trapRainWater, nextPermutation, mergeIntervals, longestConsecutive, rotateArray, subarraySum, isMonotonic, pairSum, beautifulArray |
| `Array2DProblems.java` | diagonalSum, printSpiral, rotate90, setZeroes, transpose, searchMatrix, wordSearch (DFS), numIslands (DFS) |
| `TwoSum.java` | Two Sum using HashMap — returns indices of pair summing to target |
| `LargestElementDemo.java` | largest element, array sum, isSorted check, reverse, firstRepeating, even/odd count, frequency count |

---

## Strings/

| File | Description |
|---|---|
| `StringBasics.java` | isPalindrome, capitalizeWords, compress (run-length encoding), firstNonRepeating, balancedStringSplit |
| `StringProblems.java` | shortestPath — find distance from origin after a sequence of N/S/E/W direction moves |

---

## LinkedList/

| File | Description |
|---|---|
| `SinglyLinkedList.java` | addFirst, addLast, addAtIndex, deleteAtIndex, reverse, isPalindrome, hasCycle, removeCycle, rotateByK, print |
| `DoublyLinkedList.java` | addFirst, addLast, addAtIndex, deleteAtIndex, reverse, print — full doubly linked list implementation |
| `CircularLinkedList.java` | addFirst, addLast, deleteFirst, deleteLast, print — circular linked list implementation |
| `ListNode.java` | ListNode class definition (val + next) used across LinkedList problems |
| `LinkedListProblems.java` | mergeSort (merge sort on linked list), zigzagList (reorder L1→Ln→L2→Ln-1...) |

---

## Stack/

| File | Description |
|---|---|
| `StackImplementation.java` | pushAtBottom, reverseStack, sortStack (all recursive), nextGreaterElement |
| `StackProblems.java` | isValidParentheses, stockSpan, hasDuplicateParentheses, largestRectangleInHistogram, isPalindrome (linked list), decodeString |
| `MonotonicStack.java` | next greater element, previous greater element, next smaller element, previous smaller element using monotonic stack |
| `StackVariants.java` | ArrayStack and LinkedStack implementations from scratch |

---

## Queue/

| File | Description |
|---|---|
| `QueueImplementation.java` | Circular array-based queue: add, remove, peek, isEmpty |
| `CircularQueue.java` | Full circular queue implementation with isFull, display |
| `QueueProblems.java` | generateBinaryNumbers (1 to n using queue), connectRopes (min cost using PQ), jobScheduling (greedy with deadlines) |

---

## Heap/

| File | Description |
|---|---|
| `Heap.java` | Min-heap from scratch: insert (sift up), remove (sift down), heapifyDown, buildMaxHeap, heapSort |
| `MediumQue.java` | kNearestCars (max-heap size k), connectRopes (min-heap), kWeakestRows (min-heap), slidingWindowMax (monotonic deque), KthLargest stream (min-heap size k) |
| `HardQue.java` | minTimeFillSlots (binary search + diff array), minimumEffortPath (Dijkstra), minOperationsToHalve (max-heap greedy), mergeKSortedLists (min-heap) |

---

## HashMap/

| File | Description |
|---|---|
| `HashMap.java` | Custom HashMap from scratch: chaining collision resolution, put, get, remove, containsKey, size, isEmpty, resize (rehash at load factor 0.75), display |
| `HashMapTypes.java` | HashMap (unordered), LinkedHashMap (insertion order), TreeMap (sorted) — demos of all operations: put, get, remove, iterate, floor/ceiling/headMap/tailMap |
| `HashSetTypes.java` | HashSet (unordered), LinkedHashSet (insertion order), TreeSet (sorted) — demos: add, remove, contains, union, intersection, difference, floor/ceiling/headSet/tailSet |
| `MapQuestions.java` | majorityElement, isAnagram, findItinerary (Hierholzer's algorithm), largestSubarraySumZero (prefix sum), subarraySumK (prefix sum + freq map) |
| `SetQuestions.java` | union, intersection, countDistinct, countDistinctInWindows (sliding window) |

---

## Trie/

| File | Description |
|---|---|
| `Trie.java` | Trie from scratch: TrieNode (children[26] + isEnd + prefixCount), insert, search, startsWith |
| `EasyQuestions.java` | countWordsWithPrefix (prefix count using prefixCount field), wordsWithPrefix (startsWith filter), countUniqueStrings (trie-based dedup), groupAnagrams (sorted key + HashMap) |
| `HardQuestions.java` | longestWordInDictionary (DFS on trie visiting only isEnd nodes), longestWordWithAllPrefixes (insert all + verify prefixes), wordBreak I (Trie + DP), wordBreak II (Trie + Backtracking) |

---

## Graph/

| File | Description |
|---|---|
| `Graph.java` | Graph representation: adjacency list (directed + undirected), addEdge, display |
| `BFS.java` | Breadth-first search: level-order traversal, shortest path in unweighted graph |
| `DFS.java` | Depth-first search: recursive traversal, connected components, cycle detection |
| `Dijkstra.java` | Dijkstra's shortest path algorithm using min-heap (PriorityQueue) |
| `GraphProblems.java` | topological sort (DFS + stack), detect cycle (directed/undirected), number of provinces, bipartite check |

---

## DynamicProgramming/

| File | Description |
|---|---|
| `DPBasics.java` | Fibonacci (memoization + tabulation), climbing stairs, min cost climbing stairs, house robber |
| `Knapsack.java` | 0/1 Knapsack (recursive, memoized, tabulated), unbounded knapsack, subset sum, equal partition sum |
| `DPProblems.java` | longest common subsequence, longest increasing subsequence, edit distance, coin change, matrix chain multiplication, palindromic substrings |

---

## Greedy/

| File | Description |
|---|---|
| `GreedyProblems.java` | activity selection, fractional knapsack, job scheduling with deadlines, minimum platforms, candy distribution, jump game I & II |

---

## Searching/

| File | Description |
|---|---|
| `LinearSearch.java` | Linear search in array and 2D array |
| `BinarySearch.java` | Iterative and recursive binary search |
| `SearchProblems.java` | search in rotated sorted array, find first/last position of element, find peak element, search in 2D matrix, square root using binary search |

---

## Sorting/

| File | Description |
|---|---|
| `BubbleSort.java` | Bubble sort with early exit optimization |
| `MergeSort.java` | Merge sort — divide and conquer, stable sort |
| `QuickSort.java` | Quick sort with Lomuto partition scheme |
| `HeapSort.java` | Heap sort using max-heap |
| `SortingExtras.java` | Counting sort, radix sort, insertion sort, selection sort |

---

## Recursion/

| File | Description |
|---|---|
| `Recursion.java` | factorial, fibonacci, power(x,n), reverse string, check palindrome, sum of digits, tower of Hanoi, print 1 to n / n to 1 |

---

## Backtracking/

| File | Description |
|---|---|
| `backtracking.java` | findSubsets (all 2^n subsets), permutations (all n! permutations), nQueens (N-Queens problem), gridWays (count paths in grid), solveSudoku (Sudoku solver), ratInMaze (all paths in binary maze), letterCombinations (phone keypad), WordBreak.java (Trie + DP & backtracking) |

---

## BitManipulation/

| File | Description |
|---|---|
| `BitManipulation.java` | checkEvenOdd, getIthBit, setIthBit, clearIthBit, clearRange, isPowerOf2, swapWithoutTemp (XOR), countSetBits (Brian Kernighan) |

---

## Patterns/

| File | Description |
|---|---|
| `SlidingWindow.java` | max sum subarray of size k, longest substring without repeating chars, minimum window substring |
| `TwoPointers.java` | pair with given sum (sorted), remove duplicates, container with most water, 3Sum |
| `PrefixSum.java` | range sum query, subarray sum equals k, equilibrium index |
| `DifferenceArray.java` | range update queries, increment range in O(1) using difference array |
| `FastSlowPointer.java` | detect cycle in linked list, find cycle start, find middle of linked list |
| `MergeIntervals.java` | merge overlapping intervals, insert interval, non-overlapping intervals |
| `TopKElements.java` | kth largest element, top k frequent elements, k closest points to origin |

---

## PatternPrinting/

| File | Description |
|---|---|
| `Star.java` | square of stars, right triangle, inverted triangle |
| `Triangle.java` | number pyramid, Pascal's triangle |
| `Rhombus.java` | solid and hollow rhombus |
| `Butterfly.java` | butterfly pattern |
| `HollowRectangle.java` | hollow rectangle border |
| `HollowRhombus.java` | hollow rhombus |
| `InvertedPyramid.java` | inverted number pyramid |
| `FloydTriangle.java` | Floyd's triangle (consecutive numbers) |
| `NumberPyramid.java` | number pyramid variants |
| `NumberPyramidVariant.java` | 0-1 triangle and other number patterns |
