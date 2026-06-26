#include <bits/stdc++.h>
using namespace std;

// DFS returns the expected path length from node 'u'
double dfs(int u, int parent, vector<vector<int>> &adjList)
{
    double sum_of_children_expected_lengths = 0;
    int valid_choices = 0;

    for (int neighbor : adjList[u])
    {
        // Don't go back to the city we just came from
        if (neighbor != parent)
        {
            valid_choices++;
            // Calculate expected length from child, and add 1 for the step to get there
            sum_of_children_expected_lengths += dfs(neighbor, u, adjList) + 1.0;
        }
    }

    // If there are no valid choices (we are at a leaf), the journey ends. Length is 0.
    if (valid_choices == 0)
    {
        return 0.0;
    }

    // Expected value is the sum of all possible outcomes divided by the number of choices
    return sum_of_children_expected_lengths / valid_choices;
}

int main()
{
    // Fast I/O
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    vector<vector<int>> adjList(n + 1, vector<int>());
    for (int i = 0; i < n - 1; i++)
    {
        int u, v;
        cin >> u >> v;
        // MUST be undirected
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }

    // Call DFS starting at node 1, with parent 0
    double ans = dfs(1, 0, adjList);

    // Print with high precision to avoid floating point errors
    cout << fixed << setprecision(15) << ans << "\n";

    return 0;
}