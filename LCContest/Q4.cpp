#include <vector>
#include <cstring>

using namespace std;

class Solution {
    // 4D DP Array to cache our states. 
    // Dimensions: [index][power_of_2][power_of_3][power_of_5]
    // Max index is 19. Max possible exponent is between -19 and +19. 
    // We size the exponent dimensions to 41 to safely allow a +20 offset for negative numbers.
    long long dp[20][41][41][41];
    
    int target_p2 = 0, target_p3 = 0, target_p5 = 0;
    int n;
    vector<int> nums_ref;

    // Precomputed lookup tables for the prime factors of numbers 1 through 6
    // Index represents the number, value represents the power of that prime.
    int c2[7] = {0, 0, 1, 0, 2, 0, 1}; // e.g., 4 is 2^2 (c2[4] = 2)
    int c3[7] = {0, 0, 0, 1, 0, 0, 1}; // e.g., 6 is 3^1 (c3[6] = 1)
    int c5[7] = {0, 0, 0, 0, 0, 1, 0}; // e.g., 5 is 5^1 (c5[5] = 1)

    long long solve(int idx, int p2, int p3, int p5) {
        // 1. Base Case: Reached the end of the array
        if (idx == n) {
            return (p2 == target_p2 && p3 == target_p3 && p5 == target_p5) ? 1 : 0;
        }

        // 2. Memoization Check
        // We add 20 to the exponents so negative exponents (from division) map to valid array indices.
        if (dp[idx][p2 + 20][p3 + 20][p5 + 20] != -1) {
            return dp[idx][p2 + 20][p3 + 20][p5 + 20];
        }

        // 3. Extract the prime factors for the current number using our $O(1)$ lookup arrays
        int val = nums_ref[idx];
        int d2 = c2[val], d3 = c3[val], d5 = c5[val];

        long long ways = 0;

        // 4. Branching Choices
        // Skip
        ways += solve(idx + 1, p2, p3, p5);                         
        // Multiply (Add exponents)
        ways += solve(idx + 1, p2 + d2, p3 + d3, p5 + d5);          
        // Divide (Subtract exponents)
        ways += solve(idx + 1, p2 - d2, p3 - d3, p5 - d5);          

        // 5. Cache and Return
        return dp[idx][p2 + 20][p3 + 20][p5 + 20] = ways;
    }

public:
    long long countSequences(vector<int>& nums, long long k) {
        n = nums.size();
        nums_ref = nums;

        // Step 1: Factorize the target 'k'
        long long temp_k = k;
        while (temp_k % 2 == 0) { target_p2++; temp_k /= 2; }
        while (temp_k % 3 == 0) { target_p3++; temp_k /= 3; }
        while (temp_k % 5 == 0) { target_p5++; temp_k /= 5; }

        // Step 2: Feasibility Checks
        // If k has remaining prime factors (like 7 or 11), it's impossible to reach.
        // If the required power of any prime is > 19, we can't reach it because we only have 19 numbers.
        if (temp_k > 1 || target_p2 > 19 || target_p3 > 19 || target_p5 > 19) {
            return 0;
        }

        // Initialize the DP array with -1 (uncalculated state)
        memset(dp, -1, sizeof(dp));
        
        // Start the recursion
        return solve(0, 0, 0, 0);
    }
};