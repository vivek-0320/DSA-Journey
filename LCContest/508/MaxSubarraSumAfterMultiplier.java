public class MaxSubarraSumAfterMultiplier {
    class Solution {
        public long maxSubarraySum(int[] nums, int k) {
            // Run the state machine for Multiplication, then for Division.
            // Return whichever gives us the bigger score!
            long maxMultiply = solve(nums, k, true);
            long maxDivide = solve(nums, k, false);

            return Math.max(maxMultiply, maxDivide);
        }

        private long solve(int[] nums, int k, boolean isMultiply) {
            // A very small number to prevent underflow errors when adding
            long MIN = (long) -1e16;

            // dp[0] = Max sum in Phase 0 (Before operation)
            // dp[1] = Max sum in Phase 1 (During operation)
            // dp[2] = Max sum in Phase 2 (After operation)
            long[] dp = { MIN, MIN, MIN };
            long maxSum = MIN;

            for (int x : nums) {
                // The value of this specific number if we apply the operation
                long opX = isMultiply ? ((long) x * k) : (x / k);

                // Calculate what our phases will look like AFTER taking this number.
                // We must save them to temporary variables first so we don't overwrite
                // the old dp values while we are still doing math with them!

                // PHASE 0: We can start a brand new normal subarray here, OR extend the current
                // Phase 0
                long next0 = Math.max((long) x, dp[0] + x);

                // PHASE 1: We can start a brand new modified subarray here,
                // OR transition into Phase 1 from Phase 0,
                // OR just continue an existing Phase 1
                long next1 = Math.max(opX, Math.max(dp[0] + opX, dp[1] + opX));

                // PHASE 2: We can transition out of Phase 1, OR continue an existing Phase 2
                long next2 = Math.max(dp[1] + x, dp[2] + x);

                // Commit our calculations to the array for the next loop
                dp[0] = next0;
                dp[1] = next1;
                dp[2] = next2;

                // The problem requires EXACTLY one operation to be performed.
                // Therefore, a valid answer must have reached at least Phase 1.
                maxSum = Math.max(maxSum, Math.max(dp[1], dp[2]));
            }

            return maxSum;
        }
    }
}
