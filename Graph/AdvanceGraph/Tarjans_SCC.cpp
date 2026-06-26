#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Graph
{
    int V;
    vector<vector<int>> adj;

public:
    Graph(int V)
    {
        this->V = V;
        adj.resize(V);
    }

    void addEdge(int u, int v)
    {
        adj[u].push_back(v);
    }

    int timer = 0;

    // OPTIMIZATION 1: Removed the 'vis' array. 
    void dfs(int u, vector<int> &tin, vector<int> &low, vector<bool> &onStack, stack<int> &stack, vector<vector<int>> &all_sccs)
    {
        timer++;
        tin[u] = timer;
        low[u] = tin[u];
        stack.push(u);
        onStack[u] = true;

        for (int v : adj[u])
        {
            if (tin[v] == -1) // Unvisited check using 'tin'
            {
                dfs(v, tin, low, onStack, stack, all_sccs);
                low[u] = min(low[u], low[v]);
            }
            else if (onStack[v])
            {
                low[u] = min(low[u], tin[v]);
            }
        }
        
        if (tin[u] == low[u])
        {
            vector<int> scc;
            int v;
            
            do {
                v = stack.top();
                stack.pop();
                onStack[v] = false;
                scc.push_back(v);
            } while (v != u);
            
            all_sccs.push_back(std::move(scc)); 
        }
    }

    vector<vector<int>> getSCCs()
    {
        stack<int> Stack;
        vector<int> tin(V, -1);
        vector<int> low(V, -1);
        vector<bool> on_stack(V, false);
        vector<vector<int>> all_sccs;
        
        for(int i = 0; i < V; i++)
        {
            if(tin[i] == -1)
                dfs(i, tin, low, on_stack, Stack, all_sccs);
        }
        return all_sccs;
    }
};

int main()
{
    Graph g(5);
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);

    vector<vector<int>> sccs = g.getSCCs();

    cout << "Strongly Connected Components:\n";
    for (const auto &scc : sccs)
    {
        for (int node : scc)
        {
            cout << node << " ";
        }
        cout << "\n";
    }

    return 0;
}