#include <bits/stdc++.h>
using namespace std;

int timer = 0;

void dfs(int u, int parent, vector<vector<int>> &adjList, vector<int> &tin, vector<int> &low, vector<bool> &isAP)
{
    tin[u] = low[u] = ++timer;
    int children = 0; // 1. Track independent branches

    for (int v : adjList[u])
    {
        if (v == parent)
            continue;

        if (tin[v] == -1)
        {
            children++;
            dfs(v, u, adjList, tin, low, isAP);
            low[u] = min(low[u], low[v]);

            // 2. The Articulation Point Conditions
            // Case A: u is NOT the root, and the mathematical condition holds
            if (parent != -1 && low[v] >= tin[u])
            {
                isAP[u] = true; // 3. Use boolean array to avoid duplicates
            }
        }
        else
        {
            low[u] = min(low[u], tin[v]);
        }
    }

    // Case B: u IS the root, and it has more than 1 independent child
    if (parent == -1 && children > 1)
    {
        isAP[u] = true;
    }
}

void criticalConnections(int n, vector<vector<int>> &connections)
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
    vector<bool> isAP;
    for (int i = 0; i < n; i++)
    {
        if (tin[i] == -1)
            dfs(i, -1, adjList, tin, low, isAP);
    }
}

int main()
{
    return 0;
}