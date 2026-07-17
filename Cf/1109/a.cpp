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

void solve()
{
    int n;
    cin >> n;
    string s;
    cin >> s;

    int count = 0;
    int maxCount = 0;
    for (int i = 0; i < n; i++)
    {
        if (s[i] == '#')
        {
            count++;
            maxCount = max(maxCount, count);
        }
        else
        {
            count = 0;
        }
    }
    cout << (maxCount + 1) / 2 << endl;
}

int main()
{
    // Optimize standard I/O operations for speed
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