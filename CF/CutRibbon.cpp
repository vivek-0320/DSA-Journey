#include <bits/stdc++.h>
using namespace std;
int main()
{
    int n, a, b, c;
    cin >> n >> a >> b >> c;
    vector<int> dp(n+1,-1e9);
    dp[0] = 0;
    for (int i = 1; i <= n; i++)
    {
        int takeA = -1e9, takeB = -1e9, takeC = -1e9;
        if(i>=a)
            takeA = dp[i-a];
        if(i>=b)
            takeB = dp[i-b];
        if(i>=c)
            takeC = dp[i-c];

        dp[i] = max(takeA,max(takeB,takeC)) + 1;
    }
    cout << dp[n] << endl;
    return 0;
}