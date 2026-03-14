#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int beautySum(string s) {
        int sum = 0;
        int n = s.length();

        // Outer loop: Pick the starting character of the substring
        for (int i = 0; i < n; i++) {
            
            int freq[26] = {0}; // Reset the tally for the new starting position
            int maxCount = 0;

            // Inner loop: Expand the substring to the right
            for (int j = i; j < n; j++) {
                
                freq[s[j] - 'a']++; // Update our running tally
                
                // Update max frequency
                if (freq[s[j] - 'a'] > maxCount) {
                    maxCount = freq[s[j] - 'a'];
                }

                // Calculate min frequency currently in the substring
                int minCount = 100000; // Initialize to a high number
                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0 && freq[k] < minCount) {
                        minCount = freq[k];
                    }
                }

                // Add the beauty of this specific substring s[i...j] to the total
                sum += (maxCount - minCount);
            }
        }
        return sum;
    }
};

int main()
{
    return 0;
}