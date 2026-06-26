#include<bits/stdc++.h>
using namespace std;

int maxElement(vector<int> &ar)
{
    int low=0;
    int high=ar.size()-1;
    int ans=INT_MIN;
    while(low<=high)
    {
        int mid = low + (high - low)/2;
        if(ar[mid] < ar[high])
        {
            ans=max(ar[high],ans);
            high=mid-1;
        }
        else
        {
            ans=max(ar[mid],ans);
            low=mid+1;
        }
    }
    return ans;
}

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for(int &num:v)
        cin >> num;
    cout << maxElement(v) << endl;
    return 0;
}