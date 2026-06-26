public class MyCircularQueue {
    private int[] ar;
    private int capacity;
    private int front;
    private int rear;

    MyCircularQueue(int k) {
        this.capacity = k;
        this.ar = new int[k];
        this.front = -1;
        this.rear = -1;
    }

    public boolean enQueue(int value) {
        if (isFull())
            return false;
        if (isEmpty())
            front = 0;
        rear = (rear + 1) % capacity;
        ar[rear] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;

        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % capacity;

        return true;
    }

    public int Front() {
        if (isEmpty())
            return -1;
        return ar[front];

    }

    public int Rear() {
        if (isEmpty())
            return -1;
        return ar[rear];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
