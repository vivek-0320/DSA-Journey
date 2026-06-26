#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    static void nextPermutation(vector<int>& nums) 
    {
        int i;
        for(i=nums.size()-2;i>=0;i--)
        {
            if(nums[i] < nums[i+1])
            {
                break;
            }                
        }  
         
        if(i!=-1)
        {
            for(int j=nums.size()-1;j>i;j--)
            {
                if(nums[j]>nums[i])
                {
                    int t = nums[j];
                    nums[j] = nums[i];
                    nums[i] = t;
                    break;
                }
            }
        }        
        reverse(nums.begin()+i+1,nums.end());
    }
};

int main()
{
    vector<int> v = {3,1,2};
    Solution::nextPermutation(v);
    for(auto &num:v)
        cout << num << " ";
    cout << endl;

}