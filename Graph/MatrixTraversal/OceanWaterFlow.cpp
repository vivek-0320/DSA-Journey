#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int r, c;
    vector<int> dx = {1, -1, 0, 0};
    vector<int> dy = {0, 0, 1, -1};
    
    void dfs(int x, int y, vector<vector<bool>>& visited, int prevHeight, vector<vector<int>>& heights) {
        if (x < 0 || x >= r || y < 0 || y >= c) return;
        if (visited[x][y]) return;
        if (heights[x][y] < prevHeight) return;
        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            dfs(x + dx[k], y + dy[k], visited, heights[x][y], heights);
        }
    }

    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        r = heights.size();
        c = heights[0].size();
        
        vector<vector<bool>> pacific(r, vector<bool>(c, false));
        vector<vector<bool>> atlantic(r, vector<bool>(c, false));
        
        for (int i = 0; i < r; i++) {
            // Left Edge (Pacific)
            dfs(i, 0, pacific, heights[i][0], heights);
            // Right Edge (Atlantic)
            dfs(i, c - 1, atlantic, heights[i][c - 1], heights);
        }
        
        for (int j = 0; j < c; j++) {
            // Top Edge (Pacific)
            dfs(0, j, pacific, heights[0][j], heights);
            // Bottom Edge (Atlantic)
            dfs(r - 1, j, atlantic, heights[r - 1][j], heights);
        }
        
        // Find the Intersection
        vector<vector<int>> result;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.push_back({i, j});
                }
            }
        }
        
        return result;
    }
};

int main()
{
    return 0;
}