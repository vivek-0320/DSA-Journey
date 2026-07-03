#include <bits/stdc++.h>
using namespace std;

int uniquePaths(int m, int n)
{
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    dp[0][1] = 1;
    for (int x = 0; x <= m; x++)
    {
        for (int y = 0; y <= n; y++)
        {
            dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
        }
    }
    return dp[m][n];
}

int uniquePaths2(int m, int n)
{
    vector<int> dp(n, 1); // The first row is all 1s

    for (int x = 1; x < m; x++)
    {
        for (int y = 1; y < n; y++)
        {
            dp[y] = dp[y] + dp[y - 1]; // Look UP + Look LEFT
        }
    }
    return dp[n - 1];
}

int main()
{
    return 0;
}