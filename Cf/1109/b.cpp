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

    vll v(n);
    for (ll &num : v)
    {
        cin >> num;
    }

    ll current_sum = 0;
    ll required_sum = 0;

    for (int i = 0; i < n; i++)
    {
        current_sum += v[i];
        required_sum += (i + 1);
        if (current_sum < required_sum)
        {
            cout << "NO\n";
            return;
        }
    }
    cout << "YES\n";
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