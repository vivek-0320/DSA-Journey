#include <bits/stdc++.h>
using namespace std;

int findPath(vector<vector<int>> &grid, int startingBoost) {
    int m = grid.size();
    int n = grid[0].size();
    
    // Queue stores: {x, y, cost, boostLeft}
    queue<vector<int>> que;
    que.push({0, 0, 0, startingBoost});
    
    // visited array tracks the MAXIMUM boosts we had when we reached this cell
    // Initialize with -1 (meaning never visited)
    vector<vector<int>> visited(m, vector<int>(n, -1));
    visited[0][0] = startingBoost;
    
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};
    
    while (!que.empty()) {
        auto curr = que.front();
        que.pop();
        
        int x = curr[0], y = curr[1], cost = curr[2], boost = curr[3];
        
        // BFS Guarantee: First time reaching the end is the shortest path
        if (x == m - 1 && y == n - 1) return cost;
        
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            // Bounds check
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                int nextBoost = boost;
                
                // If it's a traffic jam, we MUST consume a boost
                if (grid[nx][ny] == 1) {
                    nextBoost--;
                }
                
                // If we have enough boosts AND this path leaves us with more boosts 
                // than any previous path that reached this cell, we explore it!
                if (nextBoost >= 0 && nextBoost > visited[nx][ny]) {
                    visited[nx][ny] = nextBoost;
                    que.push({nx, ny, cost + 1, nextBoost});
                }
            }
        }
    }
    return -1; // Destination unreachable
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    if (!(cin >> t)) return 0;
    while (t--) {
        int m, n, x;
        cin >> m >> n >> x;
        vector<vector<int>> grid(m, vector<int>(n));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cin >> grid[i][j];
            }
        }
        cout << findPath(grid, x) << "\n";
    }
    return 0;
}