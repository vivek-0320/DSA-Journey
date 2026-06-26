#include <bits/stdc++.h>
using namespace std;

class myQueue
{
private:
    int *ar;
    int front;
    int rear;
    int size;

public:
    myQueue(int cap)
    {
        ar = new int[cap];
        size = cap;
        front = -1;
        rear = -1;
    }

    bool isFull()
    {
        if ((rear - front + 1) == size)
            return true;
        else if (rear + 1 == front)
            return true;
        else
            return false;
    }

    bool isEmpty()
    {
        if (rear == -1)
            return true;
        else
            return false;
    }

    void enqueue(int num)
    {
        if (isFull())
        {
            printf("Queue is Full\n");
        }
        else if (isEmpty())
        {
            front = rear = 0;
            ar[rear] = num;
        }
        else
        {
            rear++;
            rear %= size;
            ar[rear] = num;
        }
    }

    int dequeue()
    {
        if (isEmpty())
        {
            printf("Queue is Empty\n");
            return -9999;
        }
        else if (front == rear)
        {
            int x = ar[front];
            front = -1;
            rear = -1;
            return x;
        }
        else
        {
            int x = ar[front];
            front++;
            front %= size;
            return x;
        }
    }

    void display()
    {
        if (!isEmpty())
        {
            int i = front - 1;
            do
            {
                i++;
                i %= size;
                cout << ar[i] << " ";
            } while (i != rear);
            cout << endl;
        }
        else
        {
            cout << "Queue is Empty" << endl;
        }
    }
};

int main()
{
    myQueue q = myQueue(5);
    int ch;
    do
    {
        cout << "Enter choice\n1>Enqueue\t2>Deqeue\t3>Display\n";
        cin >> ch;
        switch (ch)
        {
        case 1:
            int num;
            cin >> num;
            q.enqueue(num);
            break;

        case 2:
            q.dequeue();
            break;

        case 3:
            q.display();
            break;
        }
    } while (ch <= 3);
}