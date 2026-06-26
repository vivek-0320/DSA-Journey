#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n;
    cin >> n;
    int ar[n];
    for(int i=0;i<n;i++)
    {
        cin >> ar[i];
    }
    int max=0,smax=0;
    for(int i=0;i<n;i++)
    {
        if(ar[i]>max)
        {
            smax=max;
            max=ar[i];            
        }
        else if(ar[i]<max && ar[i]>smax)
        {
            smax=ar[i];
        }
    }
    cout << "2nd Largest Element is : " << smax << endl;
}