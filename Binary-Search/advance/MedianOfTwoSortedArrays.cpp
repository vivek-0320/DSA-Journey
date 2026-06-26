#include <bits/stdc++.h>
using namespace std;

double findMedianSortedArrays(vector<int> &nums1, vector<int> &nums2)
{
    if (nums1.size() > nums2.size())
        return findMedianSortedArrays(nums2, nums1);

    int n1 = nums1.size();
    int n2 = nums2.size();
    int low = 0;
    int high = n1;
    while (low <= high)
    {
        int i = low + (high - low) / 2;
        int j = (n1 + n2 + 1) / 2 - i;
        int maxLeft1 = i == 0 ? INT_MIN : nums1[i - 1];
        int minRight1 = i == n1 ? INT_MAX : nums1[i];
        int maxLeft2 = (j == 0) ? INT_MIN : nums2[j - 1];
        int minRight2 = (j == n2) ? INT_MAX : nums2[j];
        if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1)
        {
            if ((n1 + n2) % 2 == 0)
                return ((double)(max(maxLeft1, maxLeft2)) + (double)(min(minRight1, minRight2))) / 2;
            else
                return (double)(max(maxLeft1, maxLeft2));
        }
        else if (minRight1 < maxLeft2)
            low = i + 1;
        else
            high = i - 1;
    }
    return 0.0;
}

int main()
{
    return 0;
}