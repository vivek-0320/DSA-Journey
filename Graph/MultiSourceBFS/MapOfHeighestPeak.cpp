#include <bits/stdc++.h>
using namespace std;

struct State
{
    int x;
    int y;
    int height;
};

vector<vector<int>> highestPeak(vector<vector<int>> &isWater)
{
    int m = isWater.size();
    int n = isWater[0].size();
    queue<State> que;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (isWater[i][j] == 1)
                que.push({i, j, 0});
        }
    }
    vector<vector<int>> res(m, vector<int>(n, -1));
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, 1, -1};
    while (!que.empty())
    {
        auto [x, y, currHeight] = que.front();
        que.pop();
        if (res[x][y] != -1)
            continue;
        res[x][y] = currHeight;
        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && res[nx][ny] == -1)
                que.push({nx, ny, currHeight + 1});
        }
    }
    return res;
}

class Solution {
public:
    vector<vector<int>> highestPeak(vector<vector<int>>& isWater) {
        int m = isWater.size();
        int n = isWater[0].size();
        
        // We only need to store coordinates in the queue
        queue<pair<int, int>> que;
        vector<vector<int>> res(m, vector<int>(n, -1));
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(isWater[i][j] == 1) {
                    res[i][j] = 0; // Mark as visited IMMEDIATELY
                    que.push({i, j});
                }
            }
        }
        
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, 1, -1};
        
        // Phase 2: Multi-Source BFS
        while (!que.empty()) {
            auto [x, y] = que.front();
            que.pop();
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                // If it's valid and strictly UNVISITED
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && res[nx][ny] == -1) {
                    // Calculate and mark height ON PUSH to prevent duplicate queueing
                    res[nx][ny] = res[x][y] + 1; 
                    que.push({nx, ny});
                }
            }
        }
        
        return res;
    }
};

int main()
{
    return 0;
}