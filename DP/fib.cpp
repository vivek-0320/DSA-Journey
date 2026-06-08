#include <bits/stdc++.h>
using namespace std;

/*Top Down Approach*/

vector<int> fib(100,-1);

int getFib(int n)
{
    if (n <= 1)
        return  n;
    else if (fib[n] != -1)
        return fib[n];
    else
    {
        fib[n]= getFib(n - 1) + getFib(n - 2);
        return fib[n];
    }
}

/*Bottom Up Approach*/

int getFib2(int n)
{
    vector<int> fib(100,-1);
    fib[0] = 0;
    fib[1] = 1;
    for(int i=2;i<=n;i++)
    {
        fib[i] = fib[i-2] + fib[i-1];
    }
    return fib[n];
}

int main()
{
    cout << getFib2(7) << endl;
    return 0;
}