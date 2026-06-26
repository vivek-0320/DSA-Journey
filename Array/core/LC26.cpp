#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    static int removeDuplicates(vector<int>& nums) {
        int i=0;
        while(i < (nums.size()-1))
        {
            if(nums[i]==nums[ (i+1) ])
            {
                nums.erase((nums.begin()+(i+1)));
                continue;
            }
            i++;
        }
        return nums.size();        
    }
};

int main()
{
    vector<int> nums = {0,0,1,1,1,2,2,3,3,4};
    int m = Solution::removeDuplicates(nums);
    cout << m << endl;
    for(auto &num:nums)
    {
        cout << num << " ";
    }
    cout << endl;

}