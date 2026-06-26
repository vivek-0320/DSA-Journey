public class MyDeque<T> {
    private T[] ar; 
    private int front; 
    private int rear;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyDeque(int k) {
        this.capacity = k;
        this.ar = (T[]) new Object[k];
        this.front = -1;
        this.rear = -1;
    }

    // Insert element at the front
    public boolean insertFront(T value) {
        if (front == 0)
            return false;
        if (isEmpty())
            front = rear = 0;
        else if (front > 0)
            front--;
        ar[front] = value;
        return true;

    }

    // Insert element at the rear
    public boolean insertRear(T value) {
        if (rear == capacity - 1)
            return false;
        else if (rear == -1)
            front = 0;
        rear++;
        ar[rear] = value;
        return true;
    }

    // Delete element from the front
    public boolean deleteFront() {
        if (isEmpty())
            return false;
        if (front == rear)
            front = rear = -1;
        else
            front++;
        return true;
    }

    // Delete element from the rear
    public boolean deleteRear() {
        if (isEmpty())
            return false;
        if (rear == front)
            rear = front = -1;
        else
            rear--;
        return true;
    }

    public T getFront() {
        if (front > -1)
            return ar[front];
        else
            return null;
    }

    public T getRear() {
        if (rear > -1)
            return ar[rear];
        else
            return null;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear == (capacity - 1) && front == 0);
    }
}
