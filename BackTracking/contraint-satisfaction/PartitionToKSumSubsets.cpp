#include <bits/stdc++.h>
using namespace std;

bool backtrack(vector<int> &nums, int k, vector<int> &subsets, int sum, int index)
{
    if (index == nums.size())
    {
        return true;
    }
    for (int i = 0; i < k; i++)
    {
        if (subsets[i] + nums[index] <= sum)
        {
            subsets[i] += nums[index];
            bool ans = backtrack(nums, k, subsets, sum, index + 1);
            if (ans)    return true;
            subsets[i] -= nums[index];
            if (subsets[i] == 0) break;
        }
    }
    return false;
}

bool canPartitionKSubsets(vector<int> &nums, int k)
{
    int sum = accumulate(nums.begin(), nums.end(), 0);
    if (sum % k != 0)
        return false;
    sort(nums.begin(), nums.end(), [](int a, int b)
         { return a > b; });
    vector<int> subsets(k, 0);
    return backtrack(nums, k, subsets, sum / k, 0);
}

int main()
{
    return 0;
}