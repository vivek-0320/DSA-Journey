#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static int subarraySum(vector<int>& nums, int k) 
    {
        // int count = 0; Brute Force Approach
        // for(int i=0;i<nums.size();i++)
        // {
        //     int sum=0;
        //     for(int j=i;j<nums.size();j++)
        //     {
        //         sum+=nums[j];
        //         if(sum==k)  count++;
        //     }
        // }
        // return count; 

        int count = 0;
        map<int,int> mpp;
        mpp[0]=1;
        int prefixSum = 0;
        for(int i=0;i<nums.size();i++)
        {
            prefixSum+=nums[i];
            int x = prefixSum - k;
            count += mpp[x];
            mpp[prefixSum]+= 1;
        } 
        return count;
    }
};

int main()
{
    vector<int> v = {3,-3,1,1,1};
    int x = Solution::subarraySum(v,3);
    cout << x << endl;

}