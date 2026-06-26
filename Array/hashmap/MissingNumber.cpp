#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static int missingNumber(vector<int>& nums) 
    {
        int n = nums.size();
        int sum = (n*(n+1))/2;
        int total = accumulate(nums.begin(),nums.end(),0);
        return (sum-total);
    }
};

int main()
{
    vector<int> v = {9,6,4,2,3,5,7,0,1};
    int n = Solution::missingNumber(v);
    cout << n << endl;
}