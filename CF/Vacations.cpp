#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    if (!(cin >> n)) return 0;

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    // dp[day][state] initialized to a "Poison Pill" (a very high number)
    // 1e9 represents an impossible path.
    vector<vector<int>> dp(n + 1, vector<int>(3, 1e9));

    // Base Case: Before day 1, 0 days have passed, and 0 rests have been taken.
    dp[0][0] = 0;
    dp[0][1] = 0;
    dp[0][2] = 0;

    for (int i = 1; i <= n; i++) {
        
        // 1. Vasya can ALWAYS choose to rest.
        dp[i][0] = min({dp[i-1][0], dp[i-1][1], dp[i-1][2]}) + 1;

        // 2. Vasya chooses Contest (only if a[i] == 1 or 3)
        if (a[i] == 1 || a[i] == 3) {
            // Must transition from Rest(0) or Gym(2)
            dp[i][1] = min(dp[i-1][0], dp[i-1][2]); 
        }

        // 3. Vasya chooses Gym (only if a[i] == 2 or 3)
        if (a[i] == 2 || a[i] == 3) {
            // Must transition from Rest(0) or Contest(1)
            dp[i][2] = min(dp[i-1][0], dp[i-1][1]);
        }
    }

    // The answer is the minimum rest days possible on the final day across all valid states
    int min_rests = min({dp[n][0], dp[n][1], dp[n][2]});
    cout << min_rests << "\n";

    return 0;
}