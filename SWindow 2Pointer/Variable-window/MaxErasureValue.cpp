#include <bits/stdc++.h>
using namespace std;

int maximumUniqueSubarray(vector<int> &nums)
{
    int currentSum = 0;
    int maxSum = 0;
    unordered_map<int, int> map;
    int left = 0;
    for (int right = 0; right < nums.size(); right++)
    {
        if (map.find(nums[right]) != map.end() || map[nums[right]] != 0) // Contains value in Subarray
        {
            while (map[nums[right]] != 0)
            {
                currentSum -= nums[left];
                map[nums[left]]--;
                left++;
            }
        }
        currentSum += nums[right];
        map[nums[right]]++;
        maxSum = max(maxSum,currentSum);
    }
    return maxSum;
}

int main()
{
    return 0;
}