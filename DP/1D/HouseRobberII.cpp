#include <bits/stdc++.h>
using namespace std;

int rob(vector<int> &nums)
{
    int n = nums.size();
    vector<int> dpLeft(n, 0);
    vector<int> dpRight(n, 0);
    dpLeft[0] = 0;
    dpLeft[1] = nums[0];
    for (int i = 2; i < n; i++)
    {
        int leave = dpLeft[i - 1];
        int take = dpLeft[i - 2] + nums[i - 1];
        dpLeft[i] = max(leave, take);
    }
    dpRight[n - 1] = 0;
    dpRight[n - 2] = nums[n - 1];
    for (int i = n - 3; i > -1; i--)
    {
        int leave = dpLeft[i + 1];
        int take = dpLeft[i + 2] + nums[i + 1];
        dpRight[i] = max(leave, take);
    }
    return max(dpRight[0], dpLeft[n - 1]);
}

class Solution
{
public:
    int rob(vector<int> &nums)
    {
        int n = nums.size();

        // Handle edge cases so our helper function never crashes
        if (n == 1)
            return nums[0];
        if (n == 2)
            return max(nums[0], nums[1]);

        // Timeline 1: Rob from house 0 to n-2 (Skip the last house)
        int timeline1 = robHelper(nums, 0, n - 2);

        // Timeline 2: Rob from house 1 to n-1 (Skip the first house)
        int timeline2 = robHelper(nums, 1, n - 1);

        // May the best timeline win!
        return max(timeline1, timeline2);
    }

private:
    // Your beautifully optimized O(1) space logic!
    int robHelper(vector<int> &nums, int start, int end)
    {
        int a = 0;
        int b = 0;

        for (int i = start; i <= end; i++)
        {
            int c = max(b, nums[i] + a); // max(leave, take)
            a = b;
            b = c;
        }
        return b;
    }
};

int main()
{
    return 0;
}