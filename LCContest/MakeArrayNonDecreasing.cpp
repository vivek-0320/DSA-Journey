#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    long long minOperations(vector<int> &nums)
    {
        int n = nums.size();
        vector<long long> dp(n + 1, 0);
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++)
        {
            dp[i] = max(dp[i - 1], dp[i - 1] + nums[i - 2] - nums[i - 1]);
        }
        return dp[n];
    }
};

int main()
{
    return 0;
}