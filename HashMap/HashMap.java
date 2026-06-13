public class HashMap<K, V> {

    // ─── NODE (each bucket entry) ─────────────────────────────────────────────────
    private class Node {
        K key;
        V value;
        Node next;
        Node(K key, V value) { this.key = key; this.value = value; }
    }

    // ─── FIELDS ───────────────────────────────────────────────────────────────────
    private Node[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR    = 0.75f;

    @SuppressWarnings("unchecked")
    HashMap() { buckets = new HashMap.Node[DEFAULT_CAPACITY]; }

    // ─── HASH FUNCTION ────────────────────────────────────────────────────────────
    // Map key to bucket index; use Math.abs to handle negative hashCodes.
    private int getBucket(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    // ─── PUT ──────────────────────────────────────────────────────────────────────
    // Insert or update key-value pair.
    // Collision handled by chaining (linked list in each bucket).
    // TC: O(1) avg, O(n) worst, SC: O(1)
    public void put(K key, V value) {
        int idx  = getBucket(key);
        Node cur = buckets[idx];
        while (cur != null) {
            if (cur.key.equals(key)) { cur.value = value; return; } // update existing
            cur = cur.next;
        }
        Node node = new Node(key, value); // insert at head of chain
        node.next    = buckets[idx];
        buckets[idx] = node;
        size++;
        if ((float) size / buckets.length >= LOAD_FACTOR) resize();
    }

    // ─── GET ──────────────────────────────────────────────────────────────────────
    // Return value for key, or null if not found.
    // TC: O(1) avg, O(n) worst
    public V get(K key) {
        Node cur = buckets[getBucket(key)];
        while (cur != null) {
            if (cur.key.equals(key)) return cur.value;
            cur = cur.next;
        }
        return null;
    }

    // ─── REMOVE ───────────────────────────────────────────────────────────────────
    // Delete entry by key; returns true if found and removed.
    // TC: O(1) avg, O(n) worst
    public boolean remove(K key) {
        int  idx  = getBucket(key);
        Node cur  = buckets[idx], prev = null;
        while (cur != null) {
            if (cur.key.equals(key)) {
                if (prev == null) buckets[idx] = cur.next;
                else prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur; cur = cur.next;
        }
        return false;
    }

    // ─── CONTAINS KEY ─────────────────────────────────────────────────────────────
    // TC: O(1) avg
    public boolean containsKey(K key) { return get(key) != null; }

    // ─── SIZE ─────────────────────────────────────────────────────────────────────
    public int size() { return size; }

    // ─── IS EMPTY ─────────────────────────────────────────────────────────────────
    public boolean isEmpty() { return size == 0; }

    // ─── RESIZE (rehash) ──────────────────────────────────────────────────────────
    // Double bucket array when load factor exceeded; rehash all entries.
    // TC: O(n), SC: O(n)
    @SuppressWarnings("unchecked")
    private void resize() {
        Node[] old = buckets;
        buckets = new HashMap.Node[old.length * 2];
        size = 0;
        for (Node head : old) {
            Node cur = head;
            while (cur != null) { put(cur.key, cur.value); cur = cur.next; }
        }
    }

    // ─── DISPLAY ──────────────────────────────────────────────────────────────────
    public void display() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            System.out.print("Bucket " + i + ": ");
            Node cur = buckets[i];
            while (cur != null) {
                System.out.print("[" + cur.key + "=" + cur.value + "] ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // put
        map.put("apple",  10);
        map.put("banana", 20);
        map.put("cherry", 30);
        map.put("apple",  99); // update existing key

        // get
        System.out.println(map.get("apple"));  // 99
        System.out.println(map.get("banana")); // 20
        System.out.println(map.get("mango"));  // null

        // containsKey
        System.out.println(map.containsKey("cherry")); // true
        System.out.println(map.containsKey("mango"));  // false

        // size
        System.out.println(map.size()); // 3

        // remove
        map.remove("banana");
        System.out.println(map.containsKey("banana")); // false
        System.out.println(map.size()); // 2

        // display
        map.display();

        // resize test — add many entries to trigger rehash
        HashMap<Integer, Integer> bigMap = new HashMap<>();
        for (int i = 0; i < 20; i++) bigMap.put(i, i * i);
        System.out.println(bigMap.get(7));  // 49
        System.out.println(bigMap.get(15)); // 225
        System.out.println(bigMap.size());  // 20
    }
}
