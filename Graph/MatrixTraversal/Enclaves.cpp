#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int r, c;
    vector<int> dx = {1, -1, 0, 0};
    vector<int> dy = {0, 0, 1, -1};

    void dfs(int x,int y,vector<vector<int>>& grid)
    {
        if(x < 0 || x >= r || y < 0 || y >= c || grid[x][y] == 0) return;
        grid[x][y] = 0;
        for(int k = 0; k < 4; k++)
        {
            dfs(x+dx[k], y+dy[k], grid);
        }
    }

    int numEnclaves(vector<vector<int>>& grid) {
        r = grid.size();
        c = grid[0].size();
        for(int i=0;i<r;i++)
        {
            dfs(i,0,grid);
            dfs(i,c-1,grid);
        }
        for(int j=0;j<c;j++)
        {
            dfs(0,j,grid);
            dfs(r-1,j,grid);
        }
        int count = 0;
        for(int i=1;i<r-1;i++)
        {
            for(int j=1;j<c-1;j++)
            {
                if(grid[i][j] == 1)
                    count++;
            }
        }
        return count;
    }
};

int main()
{
    return 0;
}