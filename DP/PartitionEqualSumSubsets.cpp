#include <bits/stdc++.h>
using namespace std;

bool canPartition(vector<int> &nums)
{
    int sum = accumulate(nums.begin(), nums.end(), 0);
    if (sum % 2 != 0)
        return false;

    int W = sum / 2;
    int N = nums.size();
    vector<vector<bool>> dp(N + 1, vector<bool>(W + 1, false));
    dp[0][0] = true;

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= W; j++)
        {
            bool leaveIt = dp[i - 1][j];

            bool takeIt = false;
            if (j >= nums[i - 1])
            {
                takeIt = dp[i - 1][j - nums[i - 1]];
            }
            dp[i][j] = takeIt || leaveIt;
        }
        if (dp[i][W] == true)
            return true;
    }
    return false;
}

bool canPartition2(vector<int> &nums)
{
    int sum = accumulate(nums.begin(), nums.end(), 0);
    if (sum % 2 != 0)
        return false;

    int W = sum / 2;
    int N = nums.size();
    vector<bool> dp(W + 1, false);
    dp[0] = true;

    // Step 3: Process each number
    for (int num : nums)
    {

        // Step 4: Traverse the backpack backwards!
        // Start at max capacity (W), stop when the item can no longer fit (num)
        for (int j = W; j >= num; j--)
        {

            // The Transition:
            // dp[j] (Leave it) OR dp[j - num] (Take it)
            dp[j] = dp[j] || dp[j - num];
        }

        // Pro-tip: Early exit if we hit our target sum early
        if (dp[W])
        {
            return true;
        }
    }

    return dp[W];
}

int main()
{
    return 0;
}