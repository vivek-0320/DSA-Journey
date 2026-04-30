#include <bits/stdc++.h>
using namespace std;

struct State
{
    int u;    // Current node
    int mask; // Visited nodes bitmask
    int dist; // Distance from start
};

class Solution
{
public:
    int shortestPathLength(vector<vector<int>> &graph)
    {
        int n = graph.size();

        // Fix 1: Use a vector of vectors for guaranteed zero-initialization
        // Dimensions: visited[mask][node]
        vector<vector<bool>> visited(1 << n, vector<bool>(n, false));

        queue<State> q;

        // Multi-source initialization
        for (int i = 0; i < n; i++)
        {
            q.push({i, 1 << i, 0});
            visited[1 << i][i] = true;
        }

        int targetMask = (1 << n) - 1;

        while (!q.empty())
        {
            State curr = q.front();
            q.pop();

            // If all nodes are visited, we found the shortest path
            if (curr.mask == targetMask)
            {
                return curr.dist;
            }

            // Explore neighbors
            for (int v : graph[curr.u])
            {
                int nextMask = curr.mask | (1 << v);

                // Fix 2: Update the visited ledger immediately to prevent duplicate states
                if (!visited[nextMask][v])
                {
                    visited[nextMask][v] = true;
                    q.push({v, nextMask, curr.dist + 1});
                }
            }
        }

        return 0; // Fallback return (should theoretically never be reached)
    }
};

int main()
{
    return 0;
}