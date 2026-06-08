#include <bits/stdc++.h>
using namespace std;

long long maximumSum(vector<int> &nums, int m, int l, int r)
{
    int n = nums.size();
    const long long INF = 1e18;

    vector<long long> pref(n + 1, 0);
    for (int i = 0; i < n; i++)
    {
        pref[i + 1] = pref[i] + nums[i];
    }

    // dp[i][k] = max sum using prefix up to index 'i' with exactly 'k' subarrays
    vector<vector<long long>> dp(n + 1, vector<long long>(m + 1, -INF));

    for (int i = 0; i <= n; i++)
    {
        dp[i][0] = 0;
    }

    long long max_total_sum = -INF;

    for (int k = 1; k <= m; k++)
    {
        for (int i = 1; i <= n; i++)
        {

            dp[i][k] = dp[i - 1][k];

            int min_prev = max(0, i - r);
            int max_prev = i - l;

            for (int prev = min_prev; prev <= max_prev; prev++)
            {
                if (dp[prev][k - 1] != -INF)
                {
                    long long subarray_sum = pref[i] - pref[prev];
                    dp[i][k] = max(dp[i][k], dp[prev][k - 1] + subarray_sum);
                }
            }

            if (k >= 1)
            {
                max_total_sum = max(max_total_sum, dp[i][k]);
            }
        }
    }
    return max_total_sum;
}

int main()
{
    return 0;
}