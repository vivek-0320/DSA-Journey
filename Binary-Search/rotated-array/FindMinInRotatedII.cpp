#include <bits/stdc++.h>
using namespace std;

int findMin(vector<int> &nums)
{
    int l = 0;
    int h = nums.size() - 1;
    while (l <= h)
    {
        int mid = l + (h - l) / 2;
        if (nums[mid] < nums[h])
            h=mid;
        else if ( nums[mid] > nums[h] )
            l=mid+1;
        else
             h--;
    }
    return nums[l];
}

int main()
{
    vector<vector<int>> testCases = {
        {1, 2, 3, 4, 5},
        {10},
        {2, 1},
        {3, 3, 3, 3, 3},
        {2, 2, 2, 0, 1, 2},
        {4, 5, 6, 7, 0},
        {10, 10, 10, 1, 10},
        {2, 2, 2, 2, 1, 2, 2},
        {1, 1, 1, 1, 0, 1, 1},
        {10,1,10,10,10}};
    for (auto &tc : testCases)
    {
        cout << findMin(tc) << endl;
    }
    return 0;
}