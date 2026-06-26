#include <bits/stdc++.h>
using namespace std;

int timer = 0;

void dfs(int u, int parent, vector<vector<int>> &adjList, vector<int> &tin, vector<int> &low, vector<vector<int>> &criticalEdges)
{
    tin[u] = low[u] = ++timer;
    for (int v : adjList[u])
    {
        if (v == parent)
            continue;

        if (tin[u] == -1)
        {
            dfs(v, u, adjList, tin, low, criticalEdges);
            low[u] = min(low[u], low[v]);
            if (low[v] > tin[u])
            {
                criticalEdges.push_back({u, v});
            }
        }
        else
        {
            low[u] = min(low[u],tin[v]);
        }
    }
}

vector<vector<int>> criticalConnections(int n, vector<vector<int>> &connections)
{
    vector<vector<int>> adjList(n, vector<int>());
    for (auto &edge : connections)
    {
        int u = edge[0], v = edge[1];
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }
    vector<int> tin(n, -1);
    vector<int> low(n);
    vector<vector<int>> criticalEdges;
    for (int i = 0; i < n; i++)
    {
        if (tin[i] == -1)
            dfs(i, -1, adjList, tin, low, criticalEdges);
    }
    return criticalEdges;
}

int main()
{
    return 0;
}