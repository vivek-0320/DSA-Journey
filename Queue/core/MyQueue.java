public class MyQueue<T> {
    private T[] ar;
    private int size;
    private int front;
    private int rear;
    private int currentSize;

    @SuppressWarnings("unchecked")
    MyQueue(int capacity) {
        ar = (T[]) new Object[capacity];
        this.size = capacity;
        this.front = -1;
        this.rear = -1;
        this.currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == size;
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return;
        }
        if (isEmpty())
            front = 0;
        rear++;
        ar[rear] = item;
        currentSize++;

    }

    public T dequeue() throws Exception {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            throw new IllegalStateException("Empty Queue");
        }
        T val = ar[front];
        if (front == rear)
            front = rear = -1;
        else
            front++;
        currentSize--;
        return val;
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>( 5);
        try {
            queue.enqueue(5);
            queue.enqueue(10);
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            queue.enqueue(5);
            queue.enqueue(10);
            queue.enqueue(15);
            queue.enqueue(20);
            queue.enqueue(25);
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
            System.out.println(queue.dequeue());
        } catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
