#include <bits/stdc++.h>
using namespace std;

class DSU
{
public:
    vector<long long> parent;
    vector<long long> size;

    DSU(long long n)
    {
        parent.resize(n);
        size.resize(n);
        for (long long i = 0; i < n; i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
    }

    long long find(long long node)
    {
        if (node == parent[node])
            return node;
        return parent[node] = find(parent[node]);
    }

    void Union(long long u, long long v)
    {
        long long pu = find(u);
        long long pv = find(v);

        if (pv == pu)
            return;

        if (size[pu] < size[pv])
        {
            size[pv] += size[pu];
            parent[pu] = pv;
        }
        else
        {
            size[pu] += size[pv];
            parent[pv] = pu;
        }
    }
};

int main()
{

    long long n, m;
    cin >> n >> m;
    DSU *dsu = new DSU(n + 1);
    for (long long i = 0; i < m; i++)
    {
        long long k;
        cin >> k;
        long long edges[k];
        for (int x = 0; x < k; x++)
            cin >> edges[x];
        for (long long j = 0; j < k - 1; j++)
        {
            dsu->Union(edges[j], edges[j + 1]);
        }
    }
    for (long long i = 0; i < n; i++)
    {
        cout << dsu->size[dsu->find(i+1)] << " ";
    }
    cout << endl;

    return 0;
}