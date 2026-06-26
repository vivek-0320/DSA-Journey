#include <bits/stdc++.h>
using namespace std;

void help(vector<string> &result, string &path, int limit, int open, int close)
{
    if (path.size() == limit * 2)
    {
        result.push_back(path);
        return;
    }
    if (open < limit)
    {
        path.push_back('(');
        help(result, path, limit, open + 1, close);
        path.pop_back();
    }
    if (close < open)
    {
        path.push_back(')');
        help(result, path, limit, open, close + 1);
        path.pop_back();
    }
}

vector<string> generateParenthesis(int n)
{
    vector<string> result;
    string path = "(";
    help(result, path, n, 1, 0);
    return result;
}

int main()
{
    int n;
    cin >> n;
    vector<string> result = generateParenthesis(n);

    for (auto &s : result)
    {
        cout << s << endl;
    }
    return 0;
}