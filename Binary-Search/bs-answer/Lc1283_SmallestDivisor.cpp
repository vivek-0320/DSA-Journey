#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    static int helper(vector<int> ar , int k)
    {
        int sum=0;
        for(int i=0;i<ar.size();i++)
        {
            sum += ceil((double)ar[i] / (double)k);
        }
        return sum;
    }

    static int smallestDivisor(vector<int>& nums, int threshold) 
    {
        int l=1;
        int h=*max_element(nums.begin(),nums.end());
        while(l+1<h)
        {
            int mid = (l+h)/2;
            if(helper(nums,mid)<=threshold)
                h=mid;
            else
                l=mid;
        }
        return h;
    }
};

int main()
{
    vector<int> v = {1,2,5,9};
    int x = Solution::smallestDivisor(v,6);
    cout << x << endl;
    return 0;
}