#include<bits/stdc++.h>
using namespace std;

class Q
{
private:
    stack<int> s1;
    stack<int> s2;

public:
    void enqueue(int num)
    {
        if(s1.empty())
        {
            s1.push(num);
        }
        else
        {
            while(!s1.empty())
            {
                int x=s1.top();
                s1.pop();
                s2.push(x);
            }
            s1.push(num);
            while(!s2.empty())
            {
                int x=s2.top();
                s2.pop();
                s1.push(x);
            }
        }
    }

    int dequeue()
    {
        if(s1.empty())
        {
            cout << "Queue is Empty\n";
            return -9999;
        }
        else
        {
            int x=s1.top();
            s1.pop();
            return x;
        }
    }
};

int main()
{
    Q q=Q();
    q.dequeue();
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    int x = q.dequeue();
    cout << x << endl;
    x = q.dequeue();
    cout << x << endl;
    x = q.dequeue();
    cout << x << endl;
    x = q.dequeue();
    cout << x << endl;

}