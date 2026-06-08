#include <bits/stdc++.h>
using namespace std;

void dfs(vector<string> &res, string &path, int n, int k, int current_cost)
{
    if (current_cost > k)
    {
        return;
    }

    if (path.length() == n)
    {
        res.push_back(path);
        return;
    }

    path.push_back('0');
    dfs(res, path, n, k, current_cost);
    path.pop_back(); // Clean up

    if (path.empty() || path.back() != '1')
    {
        path.push_back('1');
        int new_cost = current_cost + (path.length() - 1);

        dfs(res, path, n, k, new_cost);
        path.pop_back();
    }
}

vector<string> generateValidStrings(int n, int k)
{
    vector<string> res;
    string path = "";
    dfs(res, path, n, k, 0);
    return res;
}

int main()
{
    return 0;
}