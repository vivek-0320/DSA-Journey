#include <bits/stdc++.h>
using namespace std;

int islandPerimeter(vector<vector<int>> &grid)
{
    int p = 0;
    int r = grid.size();
    int c = grid[0].size();
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            if (grid[i][j] == 1) // Scanning Left to Right
            {
                p += 4;
                if(i-1 >= 0 && grid[i-1][j] == 1) // UP
                    p -= 2;
                if(j-1 >= 0 && grid[i][j-1] == 1) // LEFT
                    p -= 2;
            }
        }
    }
    return p;
}

int main()
{
    return 0;
}