#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<vector<int>> &result, vector<int> &path, int n, int k, int pos)
{
    if (path.size() == k)
    {
        result.push_back(path);
        return;
    }

    for (int i = pos;i <= n - (k - path.size()) + 1; i++)
    {
        path.push_back(i);
        backtrack(result,path,n,k,i+1);
        path.pop_back();
    }

}

vector<vector<int>> combine(int n, int k)
{
    vector<vector<int>> result;
    vector<int> path;
    backtrack(result, path, n, k, 1);
    return result;
}

int main()
{
    return 0;
}