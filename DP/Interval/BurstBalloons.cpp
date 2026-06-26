#include <bits/stdc++.h>
using namespace std;

int maxCoins(vector<int> &nums)
{
    nums.insert(nums.begin(), 1);
    nums.push_back(1);
    int n = nums.size();
    vector<vector<int>> dp(n, vector<int>(n, 0));
    for (int len = 3; len <= n; len++)
    {
        for (int i = 0; i <= n - len; i++)
        {
            int j = i + len - 1;
            for (int k = i; k < j; k++)
            {
                int cost = dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j];
                dp[i][j] = max(dp[i][j], cost);
            }
        }
    }
    return dp[0][n - 1];
}

int main()
{
    return 0;
}