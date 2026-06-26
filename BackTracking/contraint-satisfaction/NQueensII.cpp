#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    unordered_set<int> col;
    unordered_set<int> mainD;
    unordered_set<int> antiD;

    void backtrack(int &count, vector<string> &path, int n, int row)
    {
        if (row == n)
        {
            count++;
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
            backtrack(count, path, n, row + 1);
            col.erase(i);
            mainD.erase(row-i);
            antiD.erase(row+i);
            path[row][i] = '.';
        }
    }

    int totalNQueens(int n) {
        int count=0;
        vector<string> path(n, "");
        for (string &row : path)
        {
            for (int i = 0; i < n; i++)
                row += ".";
        }
        backtrack(count,path,n,0);
        return count;
    }
};

int main()
{
    return 0;
}