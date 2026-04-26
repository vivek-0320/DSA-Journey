#include <bits/stdc++.h>
using namespace std;

// An element nums[i] is considered valid if it satisfies at least one of the following conditions:

// It is strictly greater than every element to its left.
// It is strictly greater than every element to its right.
// The first and last elements are always valid.

// Return an array of all valid elements in the same order as they appear in nums.

class Solution
{
public:
    vector<int> findValidElements(vector<int> &nums)
    {
        int n = nums.size();
        vector<int> prefixMax(n);
        vector<int> suffixMax(n);
        prefixMax[0] = 0;
        suffixMax[n - 1] = 0;
        for (int i = 1; i < n; i++)
        {
            prefixMax[i] = max(prefixMax[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--)
        {
            suffixMax[i] = max(suffixMax[i + 1], nums[i + 1]);
        }
        vector<int> res;
        for (int i = 0; i < n; i++)
        {
            if (nums[i] > prefixMax[i] || nums[i] > suffixMax[i])
                res.push_back(nums[i]);
        }
        return res;
    }
};

int main()
{
    return 0;
}