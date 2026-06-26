#include <string>
#include <vector>
#include <algorithm> // Required for std::reverse

using namespace std;

string findLCS(string a, string b)
{
    int x = a.size();
    int y = b.size();
    vector<vector<int>> dp(x + 1, vector<int>(y + 1, 0));

    // Phase 1: Build the DP Table
    for (int i = 1; i <= x; i++)
    {
        for (int j = 1; j <= y; j++)
        {
            if (a[i - 1] == b[j - 1])
            {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
            else
            {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // Phase 2: Backtrack to construct the string
    string lcs = "";
    int i = x, j = y;
    
    while (i > 0 && j > 0)
    {
        // Case 1: The characters match. This character belongs in our LCS.
        if (a[i - 1] == b[j - 1])
        {
            lcs += a[i - 1]; 
            i--; // Move diagonally up-left
            j--;
        }
        // Case 2: No match. We look at the DP table to see where the larger value came from.
        // Did we inherit this max value from the cell directly above us?
        else if (dp[i - 1][j] > dp[i][j - 1]) 
        {
            i--; // Move straight up
        }
        // Case 3: We inherited the max value from the cell to our left (or they were equal)
        else 
        {
            j--; // Move straight left
        }
    }

    // Phase 3: The Reversal Trap
    // Because we started at the end of the strings and walked backwards,
    // our 'lcs' string is currently backwards. We must reverse it!
    reverse(lcs.begin(), lcs.end());

    return lcs;
}