#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        vector<vector<int>> rows(9, vector<int>(10, 0));
        vector<vector<int>> cols(9, vector<int>(10, 0));
        vector<vector<int>> boxes(9, vector<int>(10, 0));

        // Step 1: Fill lookup tables with initial state
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0';
                    rows[i][num] = 1;
                    cols[j][num] = 1;
                    boxes[(i / 3) * 3 + (j / 3)][num] = 1;
                }
            }
        }

        // Step 2: Start solving
        backtrack(board, 0, 0, rows, cols, boxes);
    }

    bool backtrack(vector<vector<char>>& board, int row, int col,
                   vector<vector<int>>& rows, vector<vector<int>>& cols, vector<vector<int>>& boxes) {
        if (row == 9)
            return true;

        if (col == 9)
            return backtrack(board, row + 1, 0, rows, cols, boxes);

        if (board[row][col] != '.')
            return backtrack(board, row, col + 1, rows, cols, boxes);

        for (int num = 1; num <= 9; num++) {
            int boxIdx = (row / 3) * 3 + (col / 3);
            if (rows[row][num] || cols[col][num] || boxes[boxIdx][num])
                continue;

            // Try placing num
            board[row][col] = num + '0';
            rows[row][num] = cols[col][num] = boxes[boxIdx][num] = 1;

            if (backtrack(board, row, col + 1, rows, cols, boxes))
                return true;

            // Undo
            board[row][col] = '.';
            rows[row][num] = cols[col][num] = boxes[boxIdx][num] = 0;
        }

        return false;
    }
};

bool validRow(vector<vector<char>> &board, int rowNo)
{
    vector<int> freq(10, 0);
    for (int i = 0; i < 9; i++)
    {
        if (board[rowNo][i] == '.')
            continue;
        int val = board[rowNo][i] - '0';
        freq[val]++;
        if (freq[val] > 1)
            return false;
    }
    return true;
}

bool validColumn(vector<vector<char>> &board, int columnNo)
{
    vector<int> freq(10, 0);
    for (int i = 0; i < 9; i++)
    {
        if (board[i][columnNo] == '.')
            continue;
        int val = board[i][columnNo] - '0';
        freq[val]++;
        if (freq[val] > 1)
            return false;
    }
    return true;
}

bool validGrid(vector<vector<char>> &board, int rowNo, int columnNo)
{
    int startRow = (rowNo / 3) * 3;
    int startColumn = (columnNo / 3) * 3;
    vector<int> freq(10, 0);

    for (int i = startRow; i < startRow + 3; i++)
    {
        for (int j = startColumn; j < startColumn + 3; j++)
        {
            if (board[i][j] == '.')
                continue;
            int val = board[i][j] - '0';
            freq[val]++;
            if (freq[val] > 1)
                return false;
        }
    }
    return true;
}

void backtrack(vector<vector<char>> &board, int row, int column, bool &solved)
{
    if (solved)
        return;
    if (row == 9)
    {
        solved = true;
        return;
    }
    if (column == 9)
    {
        backtrack(board, row + 1, 0, solved);
    }
    else if (board[row][column] != '.')
    {
        backtrack(board, row, column + 1, solved);
    }
    else
    {
        for (int i = 1; i <= 9; i++)
        {
            board[row][column] = i + '0';
            if (validRow(board, row) && validColumn(board, column) && validGrid(board, row, column))
            {
                backtrack(board, row, column + 1, solved);
                if (solved)
                    return;
            }
            board[row][column] = '.';
        }
    }
}

void solveSudoku(vector<vector<char>> &board)
{
    bool solved = false;
    backtrack(board, 0, 0, solved);
}

int main()
{
    return 0;
}