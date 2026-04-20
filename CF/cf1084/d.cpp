#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve() {
    int n, x, y;
    cin >> n >> x >> y;
    
    vector<int> p(n);
    for (int i = 0; i < n; ++i) {
        cin >> p[i];
    }
    
    // Step 1: Extract the middle part M (between portals)
    vector<int> M;
    int min_val = 1e9 + 7; // Store the minimum value in M
    int min_idx = -1;      // Store the index of the minimum value in M
    
    // Portals are at x and y. So M consists of elements from index x to y-1 (0-based)
    for (int i = x; i < y; ++i) {
        M.push_back(p[i]);
        if (p[i] < min_val) {
            min_val = p[i];
            min_idx = i - x;
        }
    }
    
    // Cyclically shift M so that the minimum element is at the very beginning
    vector<int> M_prime;
    for (int i = 0; i < M.size(); ++i) {
        M_prime.push_back(M[(min_idx + i) % M.size()]);
    }
    
    // Step 2: Extract the fixed outer sequence S (L + R)
    vector<int> S;
    for (int i = 0; i < x; ++i) {
        S.push_back(p[i]);
    }
    for (int i = y; i < n; ++i) {
        S.push_back(p[i]);
    }
    
    // Step 3: Find the optimal split point in S
    // We keep taking elements from S as long as they are smaller than M's starting value
    int k = 0;
    while (k < S.size() && S[k] < min_val) {
        k++;
    }
    
    // Step 4: Construct the final array (Prefix of S + Shifted M + Suffix of S)
    vector<int> ans;
    ans.reserve(n);
    
    for (int i = 0; i < k; ++i) ans.push_back(S[i]);
    for (int i = 0; i < M_prime.size(); ++i) ans.push_back(M_prime[i]);
    for (int i = k; i < S.size(); ++i) ans.push_back(S[i]);
    
    // Output the result
    for (int i = 0; i < n; ++i) {
        cout << ans[i] << (i == n - 1 ? "" : " ");
    }
    cout << "\n";
}

int main() {
    // Optimize standard I/O operations for performance
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    if (cin >> t) {
        while (t--) {
            solve();
        }
    }
    return 0;
}