#include <bits/stdc++.h>
using namespace std;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    while (t--)
    {
        int n, k;
        cin >> n >> k;
        string s;
        cin >> s;
        int cap[26]{};
        int sm[26]{};
        for (char ch : s)
        {
            if (ch >= 'A' && ch <= 'Z')
                cap[ch - 'A']++;
            else
                sm[ch - 'a']++;
        }
        long long pairs = 0;
        for (int i = 0; i < 26; i++)
        {
            int mx = max(cap[i], sm[i]);
            int mn = min(cap[i], sm[i]);
            pairs += mn;
            if (k > 0)
            {
                int extra = (mx - mn) / 2;
                pairs += min(k, extra);
                k = max(0, k - extra);
            }
        }
        cout << pairs << "\n";
    }
    return 0;
}