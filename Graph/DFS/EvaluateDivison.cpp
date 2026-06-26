#include <bits/stdc++.h>
using namespace std;

vector<double> calcEquation(vector<vector<string>> &equations, vector<double> &values, vector<vector<string>> &queries)
{
    unordered_map<string, vector<pair<string, double>>> graph;
    for (int i = 0; i < equations.size(); i++)
    {
        string u = equations[i][0];
        string v = equations[i][1];
        double w = values[i];

        graph[u].push_back({v, w});
        graph[v].push_back({u, (1.0 / w)});
    }
    vector<double> res(queries.size());

    for (int i = 0; i < queries.size(); i++)
    {
        string u = queries[i][0];
        string v = queries[i][1];
        
        if (graph.find(u) == graph.end() || graph.find(v) == graph.end())
            res[i] = -1.0;
        else if(u == v)
            res[i] = 1.0;
        else
        {
            queue<pair<string,double>> que;
            que.push({u,1.0});
            unordered_set<string> vis;
            vis.insert(u);
            while(!que.empty())
            {
                auto[node, val] = que.front(); que.pop();
                if(v == node)
                {
                    res[i] = val;
                    break;
                }
                for(auto neighbour : graph[node])
                {
                    if(vis.find(neighbour.first) == vis.end())
                    {
                        vis.insert(neighbour.first);
                        que.push({neighbour.first, val * neighbour.second});
                    }
                }
            }
        }
    }
    return res;
}

int main()
{
    return 0;
}