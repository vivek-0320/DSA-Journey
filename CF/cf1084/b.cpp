#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (int &num : v)
        cin >> num;

    if (n == 1)
    {
        cout << "1\n";
        return;
    }

    for (int i = 1; i < n; i++)
    {
        if (v[i] < v[i - 1])
        {
            cout << "1\n";
            return;
        }
    }

    cout << n << "\n";
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}