#include<bits/stdc++.h>
using namespace std;
int main()
{
    int n;
    cout << "Enter a number \n";
    cin >> n;
    int l=0;
    int h=max(n,2);
    while(l+1<h)
    {
        int mid = (l+h)/2;
        if(mid*mid <= n)
            l=mid;
        else
            h=mid;
    }
    cout << l << endl;
    return 0;
}