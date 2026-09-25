#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n, k;
    cin >> n >> k;
    string s;
    cin >> s;
    int b = 0;
    int w = 0;
    int l = 0;
    for (int i = 0; i < k; i++)
    {
        if (s[i] == 'B')
            b++;
        else
            w++;
    }
    int needed = w;
    for (int r = k; r < n; r++)
    {
        if (s[r] == 'B')
            b++;
        else
            w++;

        int len = r - l + 1;

        while (len > k)
        {
            if (s[l] == 'B')
                b--;
            else
                w--;

            l++;
            len = r - l + 1;
        }
        needed = min(needed, w);
    }
    cout << needed << endl;
}
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }

    return 0;
}