#include <bits/stdc++.h>
using namespace std;

bool backtrack(vector<vector<char>> &board, vector<vector<bool>> used,
               string word, int i, int j, int len)
{
    if (len == word.size())
        return true;
    if (i >= 0 && j >= 0 && i < board.size() && j < board[0].size() &&
        board[i][j] == word.at(len) && !used[i][j])
    {
        used[i][j] = true;
        bool ans = backtrack(board, used, word, i - 1, j, len + 1) ||
                   backtrack(board, used, word, i, j - 1, len + 1) ||
                   backtrack(board, used, word, i + 1, j, len + 1) ||
                   backtrack(board, used, word, i, j + 1, len + 1);
        used[i][j] = false;
        return ans;
    }
    else
    {
        return false;
    }
}

// MORE OPTIMISED BY NOT USING AN USED MATRIX

bool backtrack2(vector<vector<char>> &board, string word, int i, int j, int len)
{
    if (len == word.size())
        return true;
    if (i >= 0 && j >= 0 && i < board.size() && j < board[0].size() &&
        board[i][j] == word.at(len))
    {
        char temp = board[i][j];
        board[i][j] = '#';
        bool ans = backtrack2(board, word, i - 1, j, len + 1) ||
                   backtrack2(board, word, i, j - 1, len + 1) ||
                   backtrack2(board, word, i + 1, j, len + 1) ||
                   backtrack2(board, word, i, j + 1, len + 1);
        board[i][j] = temp;
        return ans;
    }
    else
    {
        return false;
    }
}

bool exist(vector<vector<char>> &board, string word)
{
    vector<vector<bool>> used(board.size(),
                              vector<bool>(board[0].size(), false));
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            bool ans = backtrack(board, used, word, i, j, 0);
            if (ans)
                return ans;
        }
    }
    return false;
}

int main()
{
    return 0;
}