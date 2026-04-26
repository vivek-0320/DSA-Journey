#include <bits/stdc++.h>
using namespace std;
void printMatrix(vector<vector<int>> &dp, int n)
{
    for (int k = 0; k < n; k++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << dp[k][j] << " ";
        }
        cout << endl;
    }
}

int findTheCity(int n, vector<vector<int>> &edges, int distanceThreshold)
{
    vector<vector<int>> dp(n, vector<int>(n, INT_MAX));
    for (vector<int> edge : edges)
    {
        int u = edge[0];
        int v = edge[1];
        int d = edge[2];
        dp[u][u] = 0;
        dp[v][v] = 0;
        dp[u][v] = d;
        dp[v][u] = d;
    }
    // printMatrix(dp, n);
    for (int k = 0; k < n; k++)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (dp[i][k] != INT_MAX && dp[k][j] != INT_MAX)
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[j][k]);
            }
        }
        //cout << "Through " << k << endl;
        // printMatrix(dp, n);
    }
    vector<int> citiesReached(n, 0);
    int minCities = -1;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (dp[i][j] <= distanceThreshold)
                citiesReached[i]++;
        }
        minCities = max(minCities, citiesReached[i]);
    }
    int idx = -1;
    for (int i = 0; i < n; i++)
    {
        if(citiesReached[i] <= minCities)
        {
            idx = i;
            minCities = citiesReached[i];
        }
    }
    return idx;
}

int main()
{
    int n = 4;
    vector<vector<int>> edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
    int distanceThreshold = 4;
    cout << findTheCity(n, edges, distanceThreshold) << endl;
    return 0;
}