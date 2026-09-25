#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin >> n;
    long long max_val = LONG_LONG_MIN;
    long long D = 0;
    for(int i=0;i<n;i++)
    {
        long long a;
        cin >> a;

        max_val = max(max_val,a);
        if(a < max_val)
        {
            D = max(D,max_val - a);
        }
    }
    int x = 0;
    while(D>0)
    {
        x++;
        D >>= 1;
    }
    cout << x << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while(t--)
    {
        solve();
    }

    return 0;
}