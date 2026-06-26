#include <bits/stdc++.h>
using namespace std;

vector<int> largestDivisibleSubset(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    int n = nums.size();
    vector<int> dp(n, 1); // dp[i] = size of subset till ith element
    vector<int> parent(n, -1);

    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (nums[i] % nums[j] == 0)
            {
                if (dp[j] + 1 > dp[i])
                {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }
    }
    for (int i = 0; i < n; i++)
    {
        cout << dp[i] << " ";
    }
    cout << endl;
    for (int i = 0; i < n; i++)
    {
        cout << parent[i] << " ";
    }
    cout << endl;
    int last_idx = -1;
    int max_len = 0;
    for (int i = 0; i < n; i++)
    {
        if (dp[i] > max_len)
        {
            max_len = dp[i];
            last_idx = i;
        }
    }
    vector<int> subset;
    while (last_idx != -1)
    {
        subset.push_back(nums[last_idx]);
        last_idx = parent[last_idx];
    }
    sort(subset.begin(), subset.end());
    for (int i = 0; i < subset.size(); i++)
    {
        cout << subset[i] << " ";
    }
    return subset;
}

int main()
{
    vector<int> nums = {1, 2, 4, 8};
    largestDivisibleSubset(nums);
    return 0;
}