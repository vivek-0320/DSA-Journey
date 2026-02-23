#include <vector>
#include <cstring>

using namespace std;

class Solution {
    long long dp[20][41][41][41];
    
    int target_p2 = 0, target_p3 = 0, target_p5 = 0;
    int n;
    vector<int> nums_ref;

    int c2[7] = {0, 0, 1, 0, 2, 0, 1}; 
    int c3[7] = {0, 0, 0, 1, 0, 0, 1}; 
    int c5[7] = {0, 0, 0, 0, 0, 1, 0};

    long long solve(int idx, int p2, int p3, int p5) {
        if (idx == n) {
            return (p2 == target_p2 && p3 == target_p3 && p5 == target_p5) ? 1 : 0;
        }
        if (dp[idx][p2 + 20][p3 + 20][p5 + 20] != -1) {
            return dp[idx][p2 + 20][p3 + 20][p5 + 20];
        }
        int val = nums_ref[idx];
        int d2 = c2[val], d3 = c3[val], d5 = c5[val];

        long long ways = 0;

        // Skip
        ways += solve(idx + 1, p2, p3, p5);                         
        // Multiply (Add exponents)
        ways += solve(idx + 1, p2 + d2, p3 + d3, p5 + d5);          
        // Divide (Subtract exponents)
        ways += solve(idx + 1, p2 - d2, p3 - d3, p5 - d5);          
        return dp[idx][p2 + 20][p3 + 20][p5 + 20] = ways;
    }

public:
    long long countSequences(vector<int>& nums, long long k) {
        n = nums.size();
        nums_ref = nums;
        long long temp_k = k;
        while (temp_k % 2 == 0) { target_p2++; temp_k /= 2; }
        while (temp_k % 3 == 0) { target_p3++; temp_k /= 3; }
        while (temp_k % 5 == 0) { target_p5++; temp_k /= 5; }

        // If k has remaining prime factors (like 7 or 11), it's impossible to reach.
        // If the required power of any prime is > 19, we can't reach it because we only have 19 numbers.
        if (temp_k > 1 || target_p2 > 19 || target_p3 > 19 || target_p5 > 19) {
            return 0;
        }
        memset(dp, -1, sizeof(dp));
        return solve(0, 0, 0, 0);
    }
};