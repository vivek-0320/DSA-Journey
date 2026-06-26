#include<bits/stdc++.h>
using namespace std;
int main()
{
    vector<int> nums = {5,7,7,8,8,10};
    int target = 8;
    int l1 = -1;
    int h1 = nums.size();
    int l2 = -1;
    int h2 = nums.size();
    while(l1+1 < h1 || l2+1 < h2)
    {
        if(l1+1 < h1)
        {
            int mid1 = (l1+h1)/2;
            if(nums[mid1] < target)
                l1 = mid1;
            else
                h1 = mid1;
        }

        if(l2+1 < h2)
        {
            int mid2 = (l2+h2)/2;
            if(nums[mid2] <= target)
                l2 = mid2;
            else
                h2 = mid2;
        }        
    }
    cout << h1 << " " << l2 << endl;
    if(h2==nums.size() || l2==nums.size() || nums[h2]!=target || nums[l2]!=target)
    cout << -1 <<" " << -1 << endl;
    else
    cout << h1 << " " << l2 << endl;

    return 0;
}