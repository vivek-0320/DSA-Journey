#include <bits/stdc++.h>
using namespace std;

bool countPairs(vector<int> &nums, int limit,int k)
{
    int n = nums.size();
    int count = 0;
    int j = 0;
    for (int i = 0; i < n; ++i)
    {
        while (j < n && nums[j] - nums[i] <= limit)
            ++j;
        count += (j - i - 1); 
    }
    return count >= k;
}

int smallestDistancePair(vector<int> &nums, int k)
{
    sort(nums.begin(), nums.end());
    int low = -1;
    int high = nums[nums.size() - 1] - nums[0];
    while (low + 1 < high)
    {
        int mid = low + (high - low) / 2;
        if (countPairs(nums, mid, k))
            high = mid;
        else
            low = mid;
    }
    return high;
}

int main()
{
    return 0;
}