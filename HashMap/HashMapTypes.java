import java.util.*;

public class HashMapTypes {

    // ─── HASHMAP ──────────────────────────────────────────────────────────────────
    // No guaranteed order. O(1) avg for put/get/remove.
    public static void hashMapDemo() {
        System.out.println("─── HashMap ───");
        Map<String, Integer> map = new HashMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);
        map.put("apple",  9);                          // update existing key

        System.out.println(map.get("apple"));          // 9
        System.out.println(map.containsKey("cherry")); // true
        System.out.println(map.containsValue(2));      // true
        System.out.println(map.size());                // 3
        map.remove("banana");
        System.out.println(map);                       // {apple=9, cherry=1} unordered

        for (Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());

        System.out.println(map.getOrDefault("mango", 0)); // 0
        map.putIfAbsent("apple", 100);                 // won't update, key exists
        System.out.println(map.get("apple"));          // 9
    }

    // ─── LINKED HASHMAP ───────────────────────────────────────────────────────────
    // Maintains insertion order. O(1) avg for put/get/remove.
    public static void linkedHashMapDemo() {
        System.out.println("─── LinkedHashMap ───");
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);

        System.out.println(map); // {banana=2, apple=5, cherry=1} insertion order

        for (String key : map.keySet())
            System.out.print(key + " "); // banana apple cherry
        System.out.println();
    }

    // ─── TREEMAP ──────────────────────────────────────────────────────────────────
    // Sorted by natural key order (or Comparator). O(log n) for put/get/remove.
    public static void treeMapDemo() {
        System.out.println("─── TreeMap ───");
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("banana", 2);
        map.put("apple",  5);
        map.put("cherry", 1);
        map.put("date",   4);

        System.out.println(map);                   // {apple=5, banana=2, cherry=1, date=4}
        System.out.println(map.firstKey());        // apple
        System.out.println(map.lastKey());         // date
        System.out.println(map.floorKey("bo"));    // banana (largest key <= "bo")
        System.out.println(map.ceilingKey("bo"));  // cherry (smallest key >= "bo")
        System.out.println(map.headMap("cherry")); // {apple=5, banana=2}
        System.out.println(map.tailMap("cherry")); // {cherry=1, date=4}
    }

    public static void main(String[] args) {
        hashMapDemo();
        System.out.println();
        linkedHashMapDemo();
        System.out.println();
        treeMapDemo();
    }
}
