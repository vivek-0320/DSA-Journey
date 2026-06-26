import java.util.Scanner;

public class MyPQ {
    private int[] arr;
    private int size;
    private String type;

    /**
     * @param type     use "min" OR "max" for the type
     * @param capacity capacity of the heap
     */
    MyPQ(int capacity, String type) {
        this.size = 0;
        this.arr = new int[capacity + 1];
        this.type = type;
    }

    private int Parent(int i) {
        return i / 2;
    }

    private int Left(int i) {
        return 2 * i;
    }

    private int Right(int i) {
        return 2 * i + 1;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @SuppressWarnings("unused")
    private void HeapifyUpRec(int i) { // Recursive Approach
        int p = Parent(i);
        if (type.equalsIgnoreCase("min")) {
            if (i > 1 && arr[i] < arr[p]) {
                swap(i, p);
                HeapifyUp(p);
            }
        }
        if (type.equalsIgnoreCase("max")) {
            if (i > 1 && arr[i] > arr[p]) {
                swap(i, p);
                HeapifyUp(p);
            }
        }
    }

    private void HeapifyUp(int i) { // Iterative Approach
        while (i > 1) {
            int p = Parent(i);
            boolean shouldSwap = false;
            if (type.equalsIgnoreCase("min")) {
                shouldSwap = arr[i] < arr[p];
            }
            if (type.equalsIgnoreCase("max")) {
                shouldSwap = arr[i] > arr[p];
            }

            if (shouldSwap) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unused")
    private void HeapifyDownRec(int i) {
        int left = Left(i);
        int right = Right(i);
        int largest = i;
        if (type.equalsIgnoreCase("min")) {
            if (left <= size && arr[largest] > arr[left])
                largest = left;
            if (right <= size && arr[largest] > arr[right])
                largest = right;
        }
        if (type.equalsIgnoreCase("max")) {
            if (left <= size && arr[largest] < arr[left])
                largest = left;
            if (right <= size && arr[largest] < arr[right])
                largest = right;
        }
        if (largest != i) {
            swap(largest, i);
            HeapifyDownRec(largest);
        }
    }

    private void HeapifyDown(int i) { // Iterative Approach
        while (true) {
            int left = Left(i);
            int right = Right(i);
            int largest = i;
            if (type.equalsIgnoreCase("min")) {
                if (left <= size && arr[largest] > arr[left])
                    largest = left;
                if (right <= size && arr[largest] > arr[right])
                    largest = right;
            }
            if (type.equalsIgnoreCase("max")) {
                if (left <= size && arr[largest] < arr[left])
                    largest = left;
                if (right <= size && arr[largest] < arr[right])
                    largest = right;
            }
            if (largest != i) {
                swap(largest, i);
                i = largest;
            } else {
                break;
            }
        }
    }

    public void add(int num) {
        if (size + 1 == arr.length) {
            System.out.println("Queue is Full");
            return;
        }
        size++;
        arr[size] = num;
        HeapifyUp(size);
    }

    public int peek() {
        return arr[1];
    }

    public int pop() {
        if (size < 1) {
            return -9999;
        }
        int num = arr[1];
        arr[1] = arr[size];
        size--;
        HeapifyDown(1);
        return num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyPQ pq = new MyPQ(10, "max");
        boolean run = true;
        while (run) {
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    int number = sc.nextInt();
                    pq.add(number);
                    break;
                case 2:
                    System.out.println("Popped : " + pq.pop());
                    break;
                case 3:
                    System.out.println("Peek : " + pq.peek());
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }
}
