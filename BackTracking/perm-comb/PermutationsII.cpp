#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<vector<int>> &result, vector<int> &path, vector<int> &nums, vector<bool> &used)
{
    if (path.size() == nums.size())
    {
        result.push_back(path);
        return;
    }

    for (int i = 0; i < nums.size(); i++)
    {
        if (used[i] == true)
            continue;

        if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
            continue;
        path.push_back(nums[i]);
        used[i] = true;
        backtrack(result, path, nums, used);
        used[i] = false;
        path.pop_back();
    }
}

vector<vector<int>> permuteUnique(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<vector<int>> result;
    vector<int> path;
    vector<bool> used(nums.size(), false);
    backtrack(result, path, nums, used);
    return result;
}

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (auto &num : v)
        cin >> num;

    vector<vector<int>> ans = permuteUnique(v);
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