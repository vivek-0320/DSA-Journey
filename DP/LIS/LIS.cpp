#include <bits/stdc++.h>
using namespace std;

int lengthOfLIS(vector<int> &nums)
{
    int n = nums.size();
    vector<int> dp(n, 1); // dp[i] = max elements in LIS till i, every element is LIS of 1 length
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (nums[i] > nums[j])
            {
                dp[i] = max(dp[i], dp[j] + 1); // 1 + the last longest subsequence
            }
        }
    }
    return *max_element(dp.begin(), dp.end());
}
int lengthOfLIS_optimal(vector<int> &nums)
{
    int n = nums.size();
    vector<int> tails;
    tails.push_back(nums[0]);
    for (int i = 1; i < n; i++)
    {
        if (tails[tails.size() - 1] < nums[i])
        {
            tails.push_back(nums[i]);
        }
        else
        {
            int low = 0;
            int high = tails.size() - 1;

            while (low <= high)
            {
                int mid = low + (high - low) / 2;

                if (tails[mid] < nums[i])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            tails[low] = nums[i];
        }
    }
    return tails.size();
}

int main()
{
    return 0;
}