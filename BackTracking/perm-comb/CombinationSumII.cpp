#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<vector<int>> &result, vector<int> &path, vector<int> &candidates, int target, int sumNow, int pos)
{
    if (sumNow < target)
    {
        for (int i = pos; i < candidates.size(); i++)
        {
            if (i > pos && candidates[i] == candidates[i - 1]) continue;
            path.push_back(candidates[i]);
            backtrack(result, path, candidates, target, sumNow + candidates[i], i + 1);
            path.pop_back();
        }
    }
    else if (sumNow == target)
    {
        result.push_back(path);
        return;
    }
    else
    {
        return;
    }
}

vector<vector<int>> combinationSum2(vector<int> &candidates, int target)
{
    sort(candidates.begin(), candidates.end());
    vector<vector<int>> result;
    vector<int> path;
    backtrack(result, path, candidates, target, 0, 0);
    return result;
}

int main()
{
     int t;
    int n;
    cin >> n >> t;
    vector<int> v(n);
    for (auto &num : v)
        cin >> num;

    vector<vector<int>> ans = combinationSum2(v, t);
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