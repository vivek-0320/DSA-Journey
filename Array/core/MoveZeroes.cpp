#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static void moveZeroes(vector<int>& nums) 
    {
        int i=0;
        for(int j=0;j<nums.size();j++)
        {
            if(nums[j]!=0)
            {
                int t=nums[i];
                nums[i]=nums[j];
                nums[j]=t;
                i++;
            }
        }
    }
};

int main()
{
    vector<int> v = {0,1,0,3,12};
    Solution::moveZeroes(v);
    for(auto &num:v)
    {
        cout << num << " ";
    }
    cout << endl;

}