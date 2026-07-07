#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int k;
    cin >> k;
    int c3 = 0;
    int c2 = 0;
    for (int i = 0; i < k; i++)
    {
        int num;
        cin >> num;
        if (num >= 3)
            c3++;
        else if (num >= 2)
            c2++;
    }
    if (c3 > 0 || (c2 > 1))
        cout << "YES\n";
    else
        cout << "NO\n";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}