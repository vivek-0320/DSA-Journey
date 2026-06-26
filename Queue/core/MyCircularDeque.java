class MyCircularDeque {
    private int[] ar;
    private int capacity;
    private int front;
    private int rear;

    public MyCircularDeque(int k) {
        capacity = k;
        ar = new int[k];
        front = -1;
        rear = -1;
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;

        if (front == -1)
            front = rear = 0;
        else
            front = (front + capacity - 1) % capacity;

        ar[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;
            
        if (rear == -1)
            rear = front = 0;
        else
            rear = (rear + 1) % capacity;

        ar[rear] = value;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;
        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % capacity;
        return true;

    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;
        if (rear == front)
            rear = front = -1;
        else
            rear = (rear + capacity - 1) % capacity;
        return true;

    }

    public int getFront() {
        return front > -1 ? ar[front] : -1;

    }

    public int getRear() {
        return rear > -1 ? ar[rear] : -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */