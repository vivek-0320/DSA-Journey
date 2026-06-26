#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Graph {
    int V;
    vector<vector<int>> adj;
    vector<vector<int>> rev_adj; // Transposed graph

    // Step 1: Fill the stack with nodes based on finish times
    void fillOrder(int u, vector<bool>& visited, stack<int>& Stack) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                fillOrder(v, visited, Stack);
            }
        }
        // Push node to stack ONLY after all its children are processed
        Stack.push(u);
    }

    // Step 3: DFS on the transposed graph to extract the SCC
    void DFSUtil(int u, vector<bool>& visited, vector<int>& current_scc) {
        visited[u] = true;
        current_scc.push_back(u); // Add node to current SCC

        for (int v : rev_adj[u]) {
            if (!visited[v]) {
                DFSUtil(v, visited, current_scc);
            }
        }
    }

public:
    Graph(int V) {
        this->V = V;
        adj.resize(V);
        rev_adj.resize(V);
    }

    void addEdge(int u, int v) {
        adj[u].push_back(v);
        rev_adj[v].push_back(u); // Build the transposed graph simultaneously
    }

    // Main function to find and print all SCCs
    vector<vector<int>> getSCCs() {
        stack<int> Stack;
        vector<bool> visited(V, false);

        // Pass 1: Fill vertices in stack according to their finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, Stack);
            }
        }

        // Reset visited array for the second DFS pass
        fill(visited.begin(), visited.end(), false);
        
        vector<vector<int>> all_sccs;

        // Pass 2: Process all vertices in order defined by Stack
        while (!Stack.empty()) {
            int u = Stack.top();
            Stack.pop();

            // If the node is unvisited, it's the start of a new SCC
            if (!visited[u]) {
                vector<int> current_scc;
                DFSUtil(u, visited, current_scc);
                all_sccs.push_back(current_scc);
            }
        }

        return all_sccs;
    }
};

int main() {
    // Example Graph
    Graph g(5);
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);

    vector<vector<int>> sccs = g.getSCCs();

    cout << "Strongly Connected Components:\n";
    for (const auto& scc : sccs) {
        for (int node : scc) {
            cout << node << " ";
        }
        cout << "\n";
    }

    return 0;
}