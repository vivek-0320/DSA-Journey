#include <bits/stdc++.h>
using namespace std;

int sumOfGoodIntegers(int n, int k)
{
    int sum = 0;
    int x=1;
    while(abs(n-x) <= k)
    {
        if((n & x) == 0)
            sum += x;
        x++;
    }
    return sum;
}

int main()
{
    return 0;
}