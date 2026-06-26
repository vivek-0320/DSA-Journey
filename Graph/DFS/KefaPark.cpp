#include <bits/stdc++.h>
using namespace std;


void dfs(vector<vector<int>> &adjList, int u, vector<int> &cats , vector<bool> &vis,int currentCatCount, int &m,int &res)
{
    vis[u] = true;
    if(cats[u-1] == 1)
        currentCatCount += 1;
    else
        currentCatCount = 0;

    if(currentCatCount > m)
        return;

    if(u != 1 && adjList[u].size() == 1)
    {
        res += 1;
        return;
    }

    for(int n:adjList[u])
    {
        if(!vis[n])
            dfs(adjList,n,cats,vis,currentCatCount,m,res);
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n,m;
    cin >> n >> m;
    vector<int> cats(n);
    for(int &num:cats)
        cin >> num;
    vector<vector<int>> adjList(n+1,vector<int>());
    for(int i=0;i<n-1;i++)
    {
        int u,v;
        cin >> u >> v;
        adjList[u].push_back(v);
        adjList[v].push_back(u);
    }
    vector<bool> vis(n+1,false);
    int res = 0;
    dfs(adjList,1,cats,vis,0,m,res);
    printf("%d\n",res);
}