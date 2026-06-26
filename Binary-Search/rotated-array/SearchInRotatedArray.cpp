#include<bits/stdc++.h>
using namespace std;
int main()
{
    vector<int> nums = {4,5,6,7,0,1,2};
    int target = 0;
    int size = nums.size();
    int l = 0;
    int h = size-1;    
    while(l <= h)
    {
        int mid = (l+h)/2;
        if(nums[mid]==target) break;
        if(nums[l] <= nums[mid])
        {
            if(nums[l]<=target && target<nums[mid])
                h=mid-1;
            else
                l=mid+1;
        }
        else
        {
            if(nums[mid]<target && target<nums[h])
                l = mid+1;
            else
                h = mid-1;
        }
    }
    if(nums[l]==target)
        cout << l << endl;
    else
        cout << -1 << endl;

    return 0;
}