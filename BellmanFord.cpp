#include <iostream>
#include <vector>

using namespace std;

// An edge list is actually better than an adjacency list for Bellman-Ford
struct Edge {
    int u, v, weight;
};

void bellmanFord(int V, int E, int src, vector<Edge>& edges) {
    // Step 1: Initialize distances
    vector<int> dist(V, 1e9); // Use 1e9 for infinity to avoid integer overflow
    dist[src] = 0;

    // Step 2: Relax all edges V - 1 times
    for (int i = 1; i <= V - 1; i++) {
        for (int j = 0; j < E; j++) {
            int u = edges[j].u;
            int v = edges[j].v;
            int weight = edges[j].weight;
            
            if (dist[u] != 1e9 && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
            }
        }
    }

    // Step 3: Run the V-th time to check for negative cycles
    for (int j = 0; j < E; j++) {
        int u = edges[j].u;
        int v = edges[j].v;
        int weight = edges[j].weight;
        
        if (dist[u] != 1e9 && dist[u] + weight < dist[v]) {
            cout << "Graph contains a negative weight cycle!" << endl;
            return;
        }
    }

    // Print distances
    cout << "Vertex Distances from Source " << src << ":\n";
    for (int i = 0; i < V; i++) {
        cout << i << " \t\t " << dist[i] << "\n";
    }
}