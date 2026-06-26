#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    static int findMin(vector<int> &nums)
    {
        int size = nums.size();
        int l = 0;
        int h = size - 1;
        int ans = INT_MAX;
        while (l <= h)
        {
            int mid = (l + h) / 2;
            if (nums[l] <= nums[mid])
            {
                ans = min(nums[l], ans);
                l = mid + 1;
            }
            else
            {
                ans = min(nums[mid], ans);
                h = mid - 1;
            }
        }
        return ans;
    }
};

int main()
{
    vector<vector<int>> testCases = {
        {1, 2, 3, 4, 5},
        {10},
        {2, 1},
        {3, 3, 3, 3, 3},
        {2, 2, 2, 0, 1, 2},
        {4, 5, 6, 7, 0},
        {10, 10, 10, 1, 10},
        {2, 2, 2, 2, 1, 2, 2},
        {1, 1, 1, 1, 0, 1, 1}};
    for (auto &tc : testCases)
    {
        cout << Solution::findMin(tc) << endl;
    }
}