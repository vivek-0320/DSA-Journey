#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (int &num : v)
        cin >> num;

    int right = 0;
    int c1 = 0, c2 = 0, c3 = 0;

    while (right < n - 2)
    {
        if (v[right] == 1)
            c1++;
        else if (v[right] == 2)
            c2++;
        else
            c3++;

        right++;

        if (c1 >= c2 + c3)
        {
            break;
        }
    }

    if (c1 < c2 + c3)
    {
        cout << "NO\n";
        return;
    }

    int m1 = 0, m2 = 0, m3 = 0;
    while (right < n - 1)
    {
        if (v[right] == 1)
            m1++;
        else if (v[right] == 2)
            m2++;
        else
            m3++;

        right++;

        if (m1 + m2 >= m3)
        {
            break;
        }
    }

    if (m1 + m2 >= m3 && right < n)
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