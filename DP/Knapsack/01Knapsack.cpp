#include <bits/stdc++.h>
using namespace std;

// int W (maximum weight capacity of the knapsack)

// int[] wt (array of item weights)

// int[] val (array of item values)

// int n (number of items)

int knapsack(int W, vector<int> wt, vector<int> val, int n)
{
    vector<vector<int>> dp(n + 1, vector<int>(W + 1, 0));
    for (int i = 1; i <= n; i++)
    {
        for (int w = 1; w <= W; w++)
        {
            dp[i][w] = dp[i - 1][w]; // leave
            if (w > wt[i - 1]) // take if weight available
                dp[i][w] = max(dp[i - 1][w], val[i - 1] + dp[i-1][w - wt[i - 1]]);
        }
    }
    return dp[n][W];
}

int knapsack2(int W, vector<int> wt, vector<int> val, int n)
{
    // A single 1D array representing the knapsack capacities
    vector<int> dp(W + 1, 0);
    
    // Loop through every item
    for (int i = 0; i < n; i++)
    {
        // Loop through the weights RIGHT TO LEFT
        // We can stop at wt[i] because anything smaller can't fit the item anyway!
        for (int w = W; w >= wt[i]; w--)
        {
            dp[w] = max(dp[w], val[i] + dp[w - wt[i]]);
        }
    }
    
    return dp[W];
}

int unboundedKnapsack(int W, vector<int> wt, vector<int> val, int n)
{
    vector<int> dp(W + 1, 0);
    
    for (int i = 0; i < n; i++)
    {
        // The ONLY difference: Left-to-Right loop!
        // We start at wt[i] because anything smaller can't fit anyway.
        for (int w = wt[i]; w <= W; w++)
        {
            dp[w] = max(dp[w], val[i] + dp[w - wt[i]]);
        }
    }
    
    return dp[W];
}

int main()
{
    return 0;
}