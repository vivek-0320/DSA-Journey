public class PalindromicSubarraySum {
    public long maxPalindromicSubarraySum(int[] nums) {
        int n = nums.length;

        // 1. Build Prefix Sum Array (must use long to prevent overflow)
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // 2. Transform the array for Manacher's Algorithm
        int[] T = new int[2 * n + 3];
        T[0] = -2; // Left boundary guard
        T[2 * n + 2] = -3; // Right boundary guard

        for (int i = 0; i < n; i++) {
            T[2 * i + 1] = -1; // Dummy separator
            T[2 * i + 2] = nums[i]; // Actual number
        }
        T[2 * n + 1] = -1; // Final dummy separator

        // 3. Manacher's Algorithm
        int[] P = new int[T.length]; // Stores the radius/length of the palindrome
        int center = 0, rightBoundary = 0;

        long maxSum = 0;

        for (int i = 1; i < T.length - 1; i++) {
            // Find the corresponding letter on the left side of the palindrome
            int mirror = 2 * center - i;

            // If we are inside the current right boundary, copy the mirror's answer
            if (rightBoundary > i) {
                P[i] = Math.min(rightBoundary - i, P[mirror]);
            }

            // Expand the palindrome around center i
            while (T[i + 1 + P[i]] == T[i - 1 - P[i]]) {
                P[i]++;
            }

            // If palindrome expands past the current right boundary, adjust center
            if (i + P[i] > rightBoundary) {
                center = i;
                rightBoundary = i + P[i];
            }

            // 4. Calculate the sum of this maximal palindrome
            int len = P[i];
            if (len > 0) {
                // Map the transformed index back to the original array's index
                int start = (i - 1 - len) / 2;
                int end = start + len - 1;

                // Query the O(1) Prefix Sum
                long currentSum = prefix[end + 1] - prefix[start];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
    
}
