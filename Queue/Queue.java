public class Queue {

    int[] arr;
    int front, rear, size, capacity;

    Queue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0; rear = -1; size = 0;
    }

    // add element to rear
    public void add(int data) {
        if (size == capacity) { System.out.println("Queue is full"); return; }
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        size++;
    }

    // remove element from front
    public int remove() {
        if (isEmpty()) { System.out.println("Queue is empty"); return -1; }
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    // peek front element
    public int peek() {
        if (isEmpty()) { System.out.println("Queue is empty"); return -1; }
        return arr[front];
    }

    // check if queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
        System.out.println("isEmpty: " + q.isEmpty()); // true
        q.add(10); q.add(20); q.add(30);
        System.out.println("Peek: "    + q.peek());     // 10
        System.out.println("Remove: "  + q.remove());   // 10
        System.out.println("isEmpty: " + q.isEmpty()); // false
    }
}
