#include <bits/stdc++.h>
using namespace std;

bool validDiagonal(vector<string> path, int rowNo, int columnNo, int n)
{
    for (int r = rowNo - 1, c = columnNo - 1; r >= 0 && c >= 0; r--, c--)
        if (path[r][c] == 'Q')
            return false;
    for (int r = rowNo + 1, c = columnNo + 1; r < n && c < n; r++, c++)
        if (path[r][c] == 'Q')
            return false;

    for (int r = rowNo - 1, c = columnNo + 1; r >= 0 && c < n; r--, c++)
        if (path[r][c] == 'Q')
            return false;
    for (int r = rowNo + 1, c = columnNo - 1; r < n && c >= 0; r++, c--)
        if (path[r][c] == 'Q')
            return false;

    return true;
}

bool validColumn(vector<string> path, int columnNo, int n)
{
    for (int i = 0; i < n; i++)
    {
        if (path[i][columnNo] == 'Q')
            return false;
    }
    return true;
}

void backtrack(vector<vector<string>> &ans, vector<string> &path, int n, int row)
{
    if (row == n)
    {
        ans.push_back(path);
        return;
    }
    for (int i = 0; i < n; i++)
    {
        if (validColumn(path, i, n) && validDiagonal(path, row, i, n))
        {
            path[row][i] = 'Q';
            backtrack(ans, path, n, row + 1);
            path[row][i] = '.';
        }
    }
}

vector<vector<string>> solveNQueens(int n)
{
    vector<vector<string>> ans;
    if (n == 2 || n == 3)
        return ans;
    vector<string> path(n, "");
    for (string &row : path)
    {
        for (int i = 0; i < n; i++)
            row += ".";
    }
    backtrack(ans, path, n, 0);
    return ans;
}

class Solution
{
    unordered_set<int> col;
    unordered_set<int> mainD;
    unordered_set<int> antiD;

    void backtrack(vector<vector<string>> &ans, vector<string> &path, int n, int row)
    {
        if (row == n)
        {
            ans.push_back(path);
            return;
        }
        for (int i = 0; i < n; i++)
        {
            if (col.count(i) || mainD.count(row - i) || antiD.count(row + i))
                continue;

            path[row][i] = 'Q';
            col.insert(i);
            mainD.insert(row-i);
            antiD.insert(row+i);
            backtrack(ans, path, n, row + 1);
            col.erase(i);
            mainD.erase(row-i);
            antiD.erase(row+i);
            path[row][i] = '.';
        }
    }

    vector<vector<string>> solveNQueens(int n)
    {
        vector<vector<string>> ans;
        if (n == 2 || n == 3)
            return ans;
        vector<string> path(n, "");
        for (string &row : path)
        {
            for (int i = 0; i < n; i++)
                row += ".";
        }
        backtrack(ans, path, n, 0);
        return ans;
    }
};

int main()
{
    int n = 4;
    vector<string> path(n, "");
    for (string &row : path)
    {
        for (int i = 0; i < n; i++)
            row += ".";
    }
    for (string row : path)
        cout << row << endl;
    return 0;
}