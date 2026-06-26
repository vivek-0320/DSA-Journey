#include <bits/stdc++.h>
using namespace std;

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int m, n;

void dfs(vector<vector<char>> &grid, int x, int y)
{
    grid[x][y] = 0;
    for (int k = 0; k < 4; k++)
    {
        int nx = dx[k] + x;
        int ny = dy[k] + y;
        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1')
            dfs(grid,nx,ny);
    }
}

int numIslands(vector<vector<char>> &grid)
{
    m = grid.size();
    n = grid[0].size();
    int countIslands = 0;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (grid[i][j] == '1')
            {
                ++countIslands;
                dfs(grid, i, j);
            }
        }
    }
}

int main()
{
    return 0;
}