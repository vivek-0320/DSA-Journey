#include <bits/stdc++.h>
using namespace std;

int coinChange(vector<int> &coins, int amount)
{
    vector<int> dp(amount + 1, amount + 1);
    dp[0] = 0;
    for (int coin : coins)
    {
        for (int w = coin; w <= amount; w++)
        {
            dp[w] = min(dp[w], 1+dp[w-coin]);
        }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount];
}

int main()
{
    return 0;
}