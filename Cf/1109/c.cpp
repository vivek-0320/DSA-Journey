#include <bits/stdc++.h>
using namespace std;

using ll = long long;
using ld = long double;
using vi = vector<int>;
using vll = vector<ll>;
using pii = pair<int, int>;
using pll = pair<ll, ll>;

#define pb push_back
#define all(x) (x).begin(), (x).end()
#define sz(x) (int)(x).size()

const int MOD = 1e9 + 7;
const ll INF = 1e18;
const int MAXN = 2e5 + 5;

class DSU
{
    vector<int> parent;
    vector<int> size; 

public:
    DSU(int n)
    {
        parent.resize(n);
        size.resize(n, 1); 
        for (int i = 0; i < n; i++)
        {
            parent[i] = i; 
        }
    }

    int findUltimateBoss(int node)
    {
        if (node == parent[node])
        {
            return node;
        }
        return parent[node] = findUltimateBoss(parent[node]);
    }

    bool connected(int u, int v)
    {
        int ultimateBossU = findUltimateBoss(u);
        int ultimateBossV = findUltimateBoss(v);
        return ultimateBossU == ultimateBossV;
    }

    void unionBySize(int u, int v)
    {
        int ultimateBossU = findUltimateBoss(u);
        int ultimateBossV = findUltimateBoss(v);

        if (ultimateBossU == ultimateBossV)
            return;

        if (size[ultimateBossU] < size[ultimateBossV])
        {
            parent[ultimateBossU] = ultimateBossV;
            size[ultimateBossV] += size[ultimateBossU];
        }
        else
        {
            parent[ultimateBossV] = ultimateBossU;
            size[ultimateBossU] += size[ultimateBossV];
        }
    }
};

void solve()
{
    int n, x, y;
    cin >> n >> x >> y;

    vi v(n);
    for (int &num : v)
    {
        cin >> num;
    }
    
    DSU dsu = DSU(n); 
    
    for (int i = 0; i < n; i++)
    {
        if(i + x < n)
            dsu.unionBySize(i, i + x);
        if(i + y < n)
            dsu.unionBySize(i, i + y);
    }
    
    for(int i = 0; i < n; i++)
    {
        int dest = v[i] - 1; 
        if(!dsu.connected(i, dest)) 
        {
            cout << "NO\n";
            return;
        }
    }
    cout << "YES\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t = 1;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}