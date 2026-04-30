#include <bits/stdc++.h>
using namespace std;

int minPathSum(vector<vector<int>> &grid)
{
    int r = grid.size();
    int c = grid[0].size();
    vector<vector<int>> dp(r + 1, vector<int>(c + 1, 0));
    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            int cost = grid[i - 1][j - 1];
            if (i == 1)
                dp[i][j] = dp[i][j - 1] + cost;
            else if (j == 1)
                dp[i][j] = dp[i - 1][j] + cost;
            else
                dp[i][j] = min(dp[i - 1][j] + cost, dp[i][j - 1] + cost);
        }
    }
    return dp[r][c];
}

int main()
{
    return 0;
}