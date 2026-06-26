#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<vector<int>> &result, vector<int> &path, vector<int> &nums, int i)
{
    if (i >= nums.size())
    {
        result.push_back(path);
        return;
    }
    path.push_back(nums[i]);
    backtrack(result, path, nums, i + 1);
    path.pop_back();
    while (i + 1 < nums.size() && nums[i] == nums[i + 1])
        i += 1;
    backtrack(result, path, nums, i + 1);
}

vector<vector<int>> subsetsWithDup(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<vector<int>> result;
    vector<int> path;
    backtrack(result, path, nums, 0);
    return result;
}

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (auto &num : v)
        cin >> num;

    vector<vector<int>> ans = subsetsWithDup(v);
    for (const auto &row : ans)
    {
        for (int val : row)
        {
            cout << val << " ";
        }
        cout << endl;
    }
    return 0;
}