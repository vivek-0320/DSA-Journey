#include <bits/stdc++.h>
using namespace std;

int findTargetSumWays(vector<int> &nums, int target)
{
    int sum = accumulate(nums.begin(), nums.end(), 0);

    // Edge Cases: If sum is smaller than target, or the math yields an odd number
    if (abs(target) > sum || (target + sum) % 2 != 0)
        return 0;

    int P = (target + sum) / 2;
    int n = nums.size();

    vector<vector<int>> dp(n + 1, vector<int>(P + 1, 0));
    dp[0][0] = 1;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j <= P; j++)
        {
            dp[i][j] = dp[i-1][j];
            if(j >= nums[i-1])
            {
                dp[i][j] += dp[i-1][j - nums[i-1]];
            }
        }
    }
    return dp[n][P];
}

int main()
{
    return 0;
}