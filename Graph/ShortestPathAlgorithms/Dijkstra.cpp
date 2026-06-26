#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        // Using vector<pair<int,int>> where pair is {neighbor, weight}
        vector<vector<pair<int, int>>> graph(n + 1);
        for (auto& t : times) {
            graph[t[0]].push_back({t[1], t[2]});
        }

        // Use a safe infinity (1e9) to avoid overflow logic, but INT_MAX is fine if careful.
        const int INF = 1e9;
        vector<int> dist(n + 1, INF);
        
        // Store {distance, node} to sort by distance automatically
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

        dist[k] = 0;
        pq.push({0, k}); // {distance, node}

        while (!pq.empty()) {
            auto [d, u] = pq.top();
            pq.pop();

            if (d > dist[u]) continue;

            for (auto& edge : graph[u]) {
                auto [v, w] = edge;
                
                if (dist[u] + w < dist[v]) { // Relax
                    dist[v] = dist[u] + w;
                    pq.push({dist[v], v}); // Push {distance, node}
                }
            }
        }
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) return -1;
            maxTime = max(maxTime, dist[i]);
        }
        return maxTime;
    }
};

int main()
{
    return 0;
}