#include<bits/stdc++.h>
using namespace std;
int main()
{
    return 0;
}

class Solution {
public:
    int almostPalindromic(string s) {
      int n = s.length();
        if (n == 0) return 0;
        int maxLen = 0;
        for (int i = 0; i < n; ++i) {
            int l = i - 1;
            int r = i + 1;
            int currLen = 1;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
                currLen += 2;
            }
            
            maxLen = max(maxLen, currLen);
            if (l >= 0) {
                maxLen = max(maxLen, currLen + 1 + expandStrict(s, l - 1, r));
            }
            if (r < n) {
                maxLen = max(maxLen, currLen + 1 + expandStrict(s, l, r + 1));
            }
            l = i;
            r = i + 1;
            currLen = 0;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
                currLen += 2;
            }
            maxLen = max(maxLen, currLen);
            if (l >= 0) {
                maxLen = max(maxLen, currLen + 1 + expandStrict(s, l - 1, r));
            }
            if (r < n) {
                maxLen = max(maxLen, currLen + 1 + expandStrict(s, l, r + 1));
            }
        }
        
        return maxLen;
    }
    
    int expandStrict(string& s, int l, int r) {
        int count = 0;
        while (l >= 0 && r < s.length() && s[l] == s[r]) {
            l--;
            r++;
            count += 2;
        }
        return count;
    }
    
};