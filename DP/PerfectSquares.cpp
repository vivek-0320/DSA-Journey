#include <bits/stdc++.h>
using namespace std;

int numSquares(int n)
{
    vector<int> dp(n + 1, 100001);
    dp[0] = 0;
    for (int i = 1; i * i <= n; i++)
    {
        int w = i*i;
        for(int j=w;j<=n;j++)
        {
            dp[j] = min(dp[j],dp[j-w] + 1);
        }
        // for(int k=0;k<=n;k++)
        // {
        //     cout << dp[k] << " ";
        // }
        // cout << endl;
    }
    return dp[n];
}

int main()
{
    cout << numSquares(12) << endl;
    return 0;
}