package hashing;

import java.util.*;

public class HashSetExamples {

    // ─── HASHSET ──────────────────────────────────────────────────────────────────
    // No duplicates, no guaranteed order. O(1) avg for add/remove/contains.
    public static void hashSetDemo() {
        System.out.println("─── HashSet ───");
        Set<Integer> set = new HashSet<>();
        set.add(3); set.add(1); set.add(4); set.add(1); set.add(2); // 1 is duplicate

        System.out.println(set);             // [1, 2, 3, 4] unordered
        System.out.println(set.contains(3)); // true
        System.out.println(set.contains(9)); // false
        set.remove(4);
        System.out.println(set.size());      // 3

        for (int n : set) System.out.print(n + " ");
        System.out.println();

        // set operations
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));

        Set<Integer> union = new HashSet<>(a); union.addAll(b);
        System.out.println("Union: " + union);        // [1, 2, 3, 4, 5, 6]

        Set<Integer> intersection = new HashSet<>(a); intersection.retainAll(b);
        System.out.println("Intersection: " + intersection); // [3, 4]

        Set<Integer> diff = new HashSet<>(a); diff.removeAll(b);
        System.out.println("Difference: " + diff);   // [1, 2]
    }

    // ─── LINKED HASHSET ───────────────────────────────────────────────────────────
    // No duplicates, maintains insertion order. O(1) avg.
    public static void linkedHashSetDemo() {
        System.out.println("─── LinkedHashSet ───");
        Set<String> set = new LinkedHashSet<>();
        set.add("banana");
        set.add("apple");
        set.add("cherry");
        set.add("apple"); // duplicate — ignored

        System.out.println(set); // [banana, apple, cherry] insertion order preserved

        for (String s : set) System.out.print(s + " ");
        System.out.println();
    }

    // ─── TREESET ──────────────────────────────────────────────────────────────────
    // No duplicates, sorted natural order. O(log n) for add/remove/contains.
    public static void treeSetDemo() {
        System.out.println("─── TreeSet ───");
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5); set.add(1); set.add(3); set.add(2); set.add(4);
        set.add(3); // duplicate — ignored

        System.out.println(set);              // [1, 2, 3, 4, 5]
        System.out.println(set.first());      // 1
        System.out.println(set.last());       // 5
        System.out.println(set.floor(3));     // 3  (largest <= 3)
        System.out.println(set.ceiling(3));   // 3  (smallest >= 3)
        System.out.println(set.lower(3));     // 2  (strictly < 3)
        System.out.println(set.higher(3));    // 4  (strictly > 3)
        System.out.println(set.headSet(3));   // [1, 2]
        System.out.println(set.tailSet(3));   // [3, 4, 5]
        System.out.println(set.subSet(2, 5)); // [2, 3, 4]
    }

    public static void main(String[] args) {
        hashSetDemo();
        System.out.println();
        linkedHashSetDemo();
        System.out.println();
        treeSetDemo();
    }
}
