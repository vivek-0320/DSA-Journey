#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int n, k;
    cin >> n >> k;
    if (n < k)
    {
        cout << n << endl;
        return;
    }
    int count = 0;
    int work = 0;
    while(work != n)
    {
        count++;
        if(count % k == 0)
            continue;
        work++;
    }
    cout << count << endl;
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