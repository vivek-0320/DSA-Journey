#include <bits/stdc++.h>
using namespace std;

void help(int &total,vector<int> nums,int i,int xorSum)
{
    if(i >= nums.size())
    {
        total += xorSum;
        return;
    }

    help(total,nums,i+1,xorSum^nums[i]);

    help(total,nums,i+1,xorSum);
}

int subsetXORSum(vector<int> &nums)
{
    int total = 0;
    help(total,nums,0,0);
    return total;
}

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for(auto &num:v)
    {
        cin >> num;
    }
    int ans = subsetXORSum(v);
    cout << ans << endl;
    return 0;
}