#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static int findKthPositive(vector<int>& arr, int k) 
    {
        int l = 0;
        int h = arr.size()-1;
        int missed=0;
        int ans = 0;
        while(l<=h)
        {
            int mid = (l+h)/2;
            missed = arr[mid]-mid-1;
            if(missed < k)
                l=mid+1;
            else
                h=mid-1;
        }
        return l+k;        

        /*
        if (arr[0] > k) 
        return k;

        int l = 0;
        int h = arr.size();
        int missed = 0;

        while (l < h)
        {
            int mid = (l + h) / 2;
            if (arr[mid] - mid - 1 >= k)
                h = mid; 
            else 
            {
                missed = arr[mid] - mid - 1;
                l = mid + 1;
            }
        }
        return arr[l - 1] + (k - missed);
        */
    }
};

int main()
{
    vector<int> v = {2};
    cout << Solution::findKthPositive(v,1) << endl;
    return 0;
}