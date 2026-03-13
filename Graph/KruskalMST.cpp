#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

class Solution
{
    class DSU
    {
        vector<int> parent, size;

    public:
        DSU(int n)
        {
            parent.resize(n);
            size.resize(n, 1);
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int findUltimateBoss(int node)
        {
            if (node == parent[node])
                return node;
            return parent[node] = findUltimateBoss(parent[node]); // Path compression
        }

        bool unionBySize(int u, int v)
        {
            int rootU = findUltimateBoss(u);
            int rootV = findUltimateBoss(v);

            if (rootU == rootV)
                return false; // Cycle detected

            if (size[rootU] < size[rootV])
            {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            }
            else
            {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
            return true; // Successfully connected
        }
    };

    struct Edge
    {
        int u, v, weight;
        // We need this so std::sort knows how to sort our edges by weight
        bool operator<(const Edge &other) const
        {
            return weight < other.weight;
        }
    };

public:
    int minCostConnectPoints(vector<vector<int>> &points)
    {
        int n = points.size();
        vector<Edge> edges;

        // 2. Build all possible edges (Every point to every other point)
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                int weight = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]);
                edges.push_back({i, j, weight});
            }
        }

        // 3. Kruskal's Step 1: Sort edges by weight (cheapest first)
        sort(edges.begin(), edges.end());

        // 4. Kruskal's Step 2: Use DSU to pick edges without forming cycles
        DSU dsu(n);
        int total_cost = 0;
        int edges_used = 0;

        for (const auto &edge : edges)
        {
            // If they are not already connected, connect them!
            if (dsu.unionBySize(edge.u, edge.v))
            {
                total_cost += edge.weight;
                edges_used++;

                // Early exit optimization: A tree with N nodes only needs N-1 edges
                if (edges_used == n - 1)
                {
                    break;
                }
            }
        }

        return total_cost;
    }
};