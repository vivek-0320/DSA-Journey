#include<bits/stdc++.h>
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
        ar=new int[cap];
        size = cap;
        front=-1;
        rear=-1;
    }

    bool isFull()
    {
        if(rear==size-1) return true;
        else  return false;
    }

    bool isEmpty()
    {
        if(rear==-1) return true;
        else  return false;
    }

    void enqueue(int num)
    {
        if(isFull())
        {
            printf("Queue is Full\n");
        }
        else if(isEmpty())
        {
            front=0;
            rear=0;
            ar[rear]=num;
        }
        else
        {
            rear++;
            ar[rear]=num;
        }
    }

    int dequeue()
    {
        if(isEmpty())
        {
            printf("Queue is Empty\n");
            return -9999;
        }
        else if(front==rear)
        {
            int x=ar[front];
            front=-1;
            rear=-1;
            return x;
        }
        else
        {
            int x=ar[front];
            front++;
            return x;
        }
    }

    void display()
    {
        
            for(int i=front;i<=rear;i++)
            {
                cout << ar[i] << "  ";
            }
            cout << endl;
            
        
    }

};

int main()
{
    myQueue q = myQueue(5);
    q.display();
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.enqueue(40);
    q.enqueue(50);
    q.display();
    q.dequeue();
    q.display();
     q.dequeue();
    q.display();
     q.dequeue();
    q.display();
     q.dequeue();
    q.display();
     q.dequeue();
    q.display();
    q.display();

}