#include <bits/stdc++.h>
using namespace std;

bool isPossible(vector<int> nums, int k, int mid)
{
    int cuts = 0;
    int sum = 0;
    for (int num : nums)
    {
        if (sum + num <= mid)
            sum += num;
        else
        {
            cuts++;
            sum = num;
        }
    }
    return cuts + 1 <= k;
}

int splitArray(vector<int> &nums, int k)
{
    int low = *max_element(nums.begin(), nums.end()) - 1;
    int high = accumulate(nums.begin(), nums.end(), 0);
    while (low + 1 < high)
    {
        int mid = low + (high - low) / 2;
        if (isPossible(nums, k, mid))
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