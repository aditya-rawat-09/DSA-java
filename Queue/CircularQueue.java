package queue;

public class CircularQueue {

    private int[] arr;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0; rear = -1; size = 0;
    }

    // ─── ENQUEUE ──────────────────────────────────────────────────────────────────
    // Add to rear; rear wraps around using modulo.
    // TC: O(1)
    public void enqueue(int val) {
        if (isFull()) { System.out.println("Queue is full"); return; }
        rear = (rear + 1) % capacity;
        arr[rear] = val;
        size++;
    }

    // ─── DEQUEUE ──────────────────────────────────────────────────────────────────
    // Remove from front; front wraps around using modulo.
    // TC: O(1)
    public int dequeue() {
        if (isEmpty()) { System.out.println("Queue is empty"); return -1; }
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    // ─── PEEK ─────────────────────────────────────────────────────────────────────
    // TC: O(1)
    public int peek() {
        if (isEmpty()) { System.out.println("Queue is empty"); return -1; }
        return arr[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull()  { return size == capacity; }

    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(4);
        q.enqueue(10); q.enqueue(20); q.enqueue(30); q.enqueue(40);
        q.enqueue(50);                     // Queue is full
        System.out.println(q.dequeue());   // 10
        q.enqueue(50);                     // wraps around
        System.out.println(q.peek());      // 20
        System.out.println(q.dequeue());   // 20
        System.out.println(q.dequeue());   // 30
    }
}
