#include <bits/stdc++.h>
using namespace std;

int uniquePathsWithObstacles(vector<vector<int>> &obstacleGrid)
{
    int r = obstacleGrid.size();
    int c = obstacleGrid[0].size();
    vector<vector<int>> dp(r + 1, vector<int>(c + 1, 0));
    dp[0][1] = 1;
    for (int i = 1; i <= r; i++)
    {
        for (int j = 1; j <= c; j++)
        {
            if (obstacleGrid[i - 1][j - 1] == 1)
            {
                dp[i][j] = 0;
            }
            else
            {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
    }
    return dp[r][c];
}

int main()
{
    return 0;
}