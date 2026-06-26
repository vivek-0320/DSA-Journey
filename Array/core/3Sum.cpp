#include<bits/stdc++.h>
using namespace std;

vector<vector<int>> threeSum(vector<int>& nums) 
{
    vector<vector<int>> ans;
    int n= nums.size();
    sort(nums.begin(),nums.end());
    for(int k=0;k<n-2;k++)
    {
        if (k > 0 && nums[k] == nums[k - 1]) continue;

        int target = -nums[k];       
        int left=k+1,right=n-1;

        while(left < right)
        {
            int sum = nums[left] + nums[right];
            if(sum == target) {
                ans.push_back( {nums[k],nums[left++],nums[right--]} );         
                while (left < right && nums[left] == nums[left - 1]) ++left;
                while (left < right && nums[right] == nums[right + 1]) --right;           
            } else if ( sum < target)
                left++;
            else
                right--;
        }
        
    }
    return ans;
}

int main()
{
    vector<int> nums = {-1,0,1,2,-1,-4};
    vector<vector<int>> result = threeSum(nums);    
        
    for(auto triplet : result)
    {
        cout << "[";
        for(int i=0; i<triplet.size(); i++)
        {
            cout << triplet[i];
            if(i != triplet.size()-1) cout << ", ";
        }
        cout << "]" << endl;
    }           
    return 0;
}