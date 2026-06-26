#include <bits/stdc++.h>
using namespace std;

void solve(vector<vector<char>> &board)
{
    int r = board.size();
    int c = board[0].size();
    vector<int> dx = {1, -1, 0, 0};
    vector<int> dy = {0, 0, 1, -1};
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (i == 0 || i == r - 1 || j == 0 || j == c - 1)
            {
                if (board[i][j] == 'O')
                {
                    deque<pair<int, int>> deq;
                    deq.push_back({i, j});
                    board[i][j] == 'T';
                    while (!deq.empty())
                    {
                        auto curr = deq.front();
                        deq.pop_front();
                        int x = curr.first, y = curr.second;
                        for (int k = 0; k < 4; k++)
                        {
                            int nx = dx[k] + x;
                            int ny = dy[k] + y;
                            if (nx > -1 && nx < r && ny > -1 && ny < c && board[nx][ny] == 'O')
                            {
                                board[nx][ny] = 'T';
                                deq.push_back({nx, ny});
                            }
                        }
                    }
                }
            }
        }
    }
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (board[i][j] == 'O')
                board[i][j] = 'X';
            else if (board[i][j] == 'T')
                board[i][j] = 'O';
        }
    }
}

int main()
{
    return 0;
}