#include <bits/stdc++.h>
using namespace std;

int numDecodings(string s)
{
    if (s[0] == '0')
        return 0;
    int n = s.size();
    vector<int> dp(n + 1, 0);
    dp[0] = 1;
    for (int i = 1; i <= n; i++)
    {
        if (s.at(i - 1) != '0') // single digit check , extending the last path
            dp[i] = dp[i - 1];

        if (i > 1)
        {
            int num = stoi(s.substr(i - 2, 2)); // double digit  , found a new path
            if (num >= 10 && num <= 26)
                dp[i] += dp[i - 2];
        }
    }
    return dp[n];
}

int numDecodingsOptimal(string s)
{
    if (s[0] == '0')
        return 0;
    int n = s.size();
    int prev1 = 1;
    int prev2 = 1;
    for (int i = 1; i <= n; i++)
    {
        int curr = 0;
        if (s.at(i - 1) != '0') // single digit check , extending the last path
            curr += prev1;

        if (i > 1)
        {
            int num = stoi(s.substr(i - 2, 2)); // double digit  , found a new path
            if (num >= 10 && num <= 26)
                curr += prev2;
        }
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}

int main()
{
    numDecodings("11106");
    return 0;
}
