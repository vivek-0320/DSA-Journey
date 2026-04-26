#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        // Use an unordered_set for O(1) lookups
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        
        int n = s.size();
        vector<bool> dp(n + 1, false);
        
        // Base case: an empty string is always valid
        dp[0] = true; 
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // Is the string from index 0 up to j validly breakable into dictionary words?
                if (dp[j]) {
                    string word = s.substr(j, i - j);
                    if (dict.count(word)) {
                        dp[i] = true;
                        break; // The Early Exit! No need to check other 'j's
                    }
                }
            }
        }
        
        return dp[n];
    }
};

int main()
{
    return 0;
}