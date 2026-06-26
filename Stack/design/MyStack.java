public class MyStack {
    private int ar[];
    private int size;
    private int top;

    MyStack(int size) {
        this.ar = new int[size];
        this.size = size;
        this.top = -1;
    }

    boolean isFull() {
        if (top == size - 1)
            return true;
        else
            return false;
    }

    boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    void push(int num) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        top++;
        ar[top] = num;
    }

    int pop() {
        int res = -9999;
        if (!isEmpty()) {
            res = ar[top];
            top--;
        }
        return res;
    }

    public static void main(String[] args) {
        MyStack st = new MyStack(5);
        System.out.println("Pop: " + st.pop());
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        st.push(60);
        System.out.println("Pop: " + st.pop());
        System.out.println("Pop: " + st.pop());
        System.out.println("Pop: " + st.pop());
        System.out.println("Pop: " + st.pop());
        System.out.println("Pop: " + st.pop());
        System.out.println("Pop: " + st.pop());
    }

}
