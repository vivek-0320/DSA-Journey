#include <bits/stdc++.h>
using namespace std;

bool isPalindrome(string str)
{
    int l = 0;
    int r = str.size() - 1;
    while (l < r)
    {
        if(str[l++] != str[r--])
            return false;
    }
    return true;
}

void backtrack(vector<vector<string>> &ans, vector<string> path, string s, int index)
{
    if (index == s.size())
    {
        ans.push_back(path);
        return;
    }

    for (int i = index; i <= s.size(); i++)
    {
        string prefix = s.substr(index, i - index + 1);
        cout << "index: " << index << "i: " << i << " " << " " << prefix << endl;
        if (isPalindrome(prefix))
        {
            path.push_back(prefix);
            backtrack(ans, path, s, i+1);
            path.pop_back();
        }
    }
}

vector<vector<string>> partition(string s)
{
    vector<vector<string>> ans;
    vector<string> path;
    backtrack(ans, path, s, 0);
    return ans;
}

int main()
{
    string temp;
    cin >> temp;
    vector<vector<string>> res = partition(temp);
    for (auto &row : res)
    {
        for (auto &word : row)
            cout << word << " ";
        cout << endl;
    }

    return 0;
}