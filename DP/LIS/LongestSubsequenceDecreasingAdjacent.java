public class LongestSubsequenceDecreasingAdjacent {
    public int longestSubsequence(int[] nums) {
        int maxVal = 0;
        for (int num : nums)
            maxVal = Math.max(maxVal, num);

        int[][] dp = new int[maxVal + 1][maxVal + 1];
        int maxLength = 0;
        for (int num : nums) {
            for (int prev = 1; prev <= maxVal; prev++) {
                int diff = Math.abs(num - prev);

                dp[num][diff] = Math.max(dp[num][diff], dp[prev][diff] + 1);
            }
            for (int d = maxVal; d > 0; d--) {
                dp[num][d - 1] = Math.max(dp[num][d - 1], dp[num][d]);
            }
            maxLength = Math.max(maxLength, dp[num][0]);
        }
        return maxLength;
    }
}
