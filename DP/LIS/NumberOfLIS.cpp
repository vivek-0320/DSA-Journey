#include <bits/stdc++.h>
using namespace std;

int findNumberOfLIS(vector<int> &nums)
{
    int n = nums.size();
    int maxLength = -1;
    vector<int> length(1, n);
    vector<int> count(1, n);
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            if (nums[j] < nums[i])
            {
                if (length[j] + 1 > length[i])
                {
                    length[i] = length[j] + 1;
                    count[i] = count[j];
                }
                else if (length[j] + 1 == length[i])
                {
                    count[i] += count[j];
                }
            }
        }
        maxLength = max(maxLength, length[i]);
    }
    int totalLIS = 0;
    for (int i = 0; i < n; i++)
    {
        if (maxLength == length[i])
            totalLIS += count[i];
    }
    return totalLIS;
}

int main()
{
    return 0;
}