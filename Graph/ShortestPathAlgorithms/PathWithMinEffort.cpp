#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int r = -1;
    int c = -1;
    int dx[4] = {-1, 1, 0, 0};
    int dy[4] = {0, 0, 1, -1};

    bool dfs(int x, int y, int threshold, vector<vector<bool>> &vis,
             vector<vector<int>> &grid)
    {
        if (x == r - 1 && y == c - 1)
        {
            return true;
        }
        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !vis[nx][ny])
            {
                int dist = abs(grid[x][y] - grid[nx][ny]);
                if (dist <= threshold)
                {
                    vis[nx][ny] = true;
                    if (dfs(nx, ny, threshold, vis, grid) == true)
                    {
                        return true; // We found it! Stop searching and pass it
                                     // up!
                    }
                }
            }
        }
        return false;
    }

    bool isGood(int k, vector<vector<int>> &heights)
    {
        vector<vector<bool>> vis(r, vector<bool>(c, false));
        vis[0][0] = true;
        int minDiff = 1e6 + 1;
        return dfs(0, 0, k, vis, heights);
    }

    int minimumEffortPath(vector<vector<int>> &heights)
    {
        r = heights.size();
        c = heights[0].size();
        int minEl = 1e6 + 1;
        int maxEl = 0;
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                minEl = min(minEl, heights[i][j]);
                maxEl = max(maxEl, heights[i][j]);
            }
        }
        int low = -1;             // f f f f t t t
        int high = maxEl - minEl; //
        while (low + 1 < high)
        {
            int mid = low + (high - low) / 2;
            if (isGood(mid, heights))
                high = mid;
            else
                low = mid;
        }
        return high;
    }
};

class Solution2
{
public:
    int r = -1;
    int c = -1;
    int dx[4] = {-1, 1, 0, 0};
    int dy[4] = {0, 0, 1, -1};

    bool dfs(int x, int y, int threshold, vector<vector<bool>> &vis,
             vector<vector<int>> &grid)
    {
        if (x == r - 1 && y == c - 1)
        {
            return true;
        }
        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !vis[nx][ny])
            {
                int dist = abs(grid[x][y] - grid[nx][ny]);
                if (dist <= threshold)
                {
                    vis[nx][ny] = true;
                    if (dfs(nx, ny, threshold, vis, grid) == true)
                    {
                        return true; // We found it! Stop searching and pass it
                                     // up!
                    }
                }
            }
        }
        return false;
    }

    bool isGood(int k, vector<vector<int>> &heights)
    {
        vector<vector<bool>> vis(r, vector<bool>(c, false));
        vis[0][0] = true;
        int minDiff = 1e6 + 1;
        return dfs(0, 0, k, vis, heights);
    }

    bool isGoodBFS(int threshold, vector<vector<int>> &grid)
    {
        vector<vector<bool>> vis(r, vector<bool>(c, false));
        queue<pair<int, int>> q;

        // 1. Initialize the Queue
        q.push({0, 0});
        vis[0][0] = true;

        // 2. The BFS Engine
        while (!q.empty())
        {
            auto [x, y] = q.front();
            q.pop();

            // If we reached the end, instantly return true
            if (x == r - 1 && y == c - 1)
            {
                return true;
            }

            // Check all 4 neighbors
            for (int k = 0; k < 4; k++)
            {
                int nx = x + dx[k];
                int ny = y + dy[k];

                // If bounds are valid and cell is unvisited
                if (nx >= 0 && nx < r && ny >= 0 && ny < c && !vis[nx][ny])
                {
                    // If the jump is legal
                    if (abs(grid[x][y] - grid[nx][ny]) <= threshold)
                    {
                        vis[nx][ny] = true; // Mark visited BEFORE pushing
                        q.push({nx, ny});
                    }
                }
            }
        }

        // If the queue empties and we never hit the bottom-right corner
        return false;
    }

    int minimumEffortPath(vector<vector<int>> &heights)
    {
        r = heights.size();
        c = heights[0].size();
        int minEl = 1e6 + 1;
        int maxEl = 0;
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                minEl = min(minEl, heights[i][j]);
                maxEl = max(maxEl, heights[i][j]);
            }
        }
        int low = -1;             // f f f f t t t
        int high = maxEl - minEl; //
        while (low + 1 < high)
        {
            int mid = low + (high - low) / 2;
            if (isGood(mid, heights))
                high = mid;
            else
                low = mid;
        }
        return high;
    }
};

class Solution2
{
public:
    struct Edge
    {
        int maxEffort;
        int x;
        int y;

        bool operator<(const Edge &other) const
        {
            return maxEffort > other.maxEffort;
        }
    };

    int minimumEffortPath(vector<vector<int>> &heights)
    {

        int r = heights.size();
        int c = heights[0].size();
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, 1, -1};

        vector<vector<int>> minEffort(r, vector<int>(c, 1e6 + 1));
        priority_queue<Edge> q;

        q.push({0, 0, 0});
        minEffort[0][0] = 0;

        while (!q.empty())
        {
            auto [currEffort, x, y] = q.top();
            q.pop();

            if (currEffort > minEffort[x][y])
                continue;

            if (x == r - 1 && y == c - 1)
                return currEffort;

            for (int k = 0; k < 4; k++)
            {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c)
                {
                    int jumpCost = abs(heights[x][y] - heights[nx][ny]);
                    int newEffort = max(currEffort, jumpCost);
                    if (newEffort < minEffort[nx][ny])
                    {
                        minEffort[nx][ny] = newEffort;
                        q.push({newEffort, nx, ny});
                    }
                }
            }
        }
        return 0;
    }
};
int main()
{
    return 0;
}