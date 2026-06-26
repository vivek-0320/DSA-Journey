#include <bits/stdc++.h>
using namespace std;

int rob(vector<int> &nums)
{
    int n = nums.size();
    vector<int> dp(n + 1, 0);
    int maxProfit = nums[0];
    for (int i = 1; i <= n; i++)
    {
        int leave = dp[i - 1];
        int take = i - 2 >= 0 ? nums[i - 1] + dp[i - 2] : nums[i - 1];
        dp[i] = max(leave, take);
        maxProfit = max(maxProfit, dp[i]);
    }
    return maxProfit;
}

int rob2(vector<int> &nums)
{
    int a = 0; // Represents dp[i-2]
    int b = 0; // Represents dp[i-1]

    for (int x : nums)
    {
        int c = max(b, x + a); // max(leave, take)
        a = b;                 // Shift window forward
        b = c;                 // Shift window forward
    }

    return b;
}

int main()
{
    return 0;
}