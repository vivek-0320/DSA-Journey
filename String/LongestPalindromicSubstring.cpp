#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        if (n < 2) return s;

        int maxLen = 0;
        int startIdx = 0;

        // Lambda function to handle the expansion to avoid duplicate code
        auto expandAroundCenter = [&](int l, int r) {
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            // l and r have crossed the boundary of the valid palindrome
            int currentLen = r - l - 1; 
            
            if (currentLen > maxLen) {
                maxLen = currentLen;
                startIdx = l + 1;
            }
        };

        for (int i = 0; i < n; i++) {
            expandAroundCenter(i, i);     // Check odd length palindrome
            expandAroundCenter(i, i + 1); // Check even length palindrome
        }

        // Only create the substring once at the very end!
        return s.substr(startIdx, maxLen);
    }
};

int main()
{
    return 0;
}