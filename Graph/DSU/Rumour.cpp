#include <bits/stdc++.h>
using namespace std;

const long long INF = 1e18; // A very large number to initialize our minimums

// DFS to explore all friends in a single connected group
void dfs(int u, vector<vector<int>> &adj, vector<bool> &vis, vector<long long> &gold, long long &min_gold) {
    vis[u] = true;
    
    // Track the absolute cheapest person we find in this specific group
    min_gold = min(min_gold, gold[u]);
    
    // Visit all friends
    for (int v : adj[u]) {
        if (!vis[v]) {
            dfs(v, adj, vis, gold, min_gold);
        }
    }
}

int main() {
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    if (!(cin >> n >> m)) return 0;

    // Use n + 1 to safely handle 1-based indexing
    vector<long long> gold(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> gold[i];
    }
    
    // Build the graph using an Adjacency List
    vector<vector<int>> adj(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u); // Because friendship goes both ways
    }

    vector<bool> vis(n + 1, false);
    long long total_cost = 0;

    // Iterate through every person
    for (int i = 1; i <= n; i++) {
        // If we haven't visited them, they belong to a new, unexplored friend group
        if (!vis[i]) {
            long long min_gold = INF; 
            dfs(i, adj, vis, gold, min_gold);
            
            // Add the cheapest cost from this newly discovered group to the total
            total_cost += min_gold;
        }
    }

    cout << total_cost << "\n";
    return 0;
}