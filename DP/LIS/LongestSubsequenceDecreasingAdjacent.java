import java.util.Arrays;

public class LongestSubsequenceDecreasingAdjacent {
    public int longestSubsequence(int[] nums) {
        int[][] dp = new int[301][301];
        
        int maxLength = 1;
        
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int[] tempDP = new int[301];
            
            for (int pastValue = 1; pastValue <= 300; pastValue++) {
                if (dp[pastValue][0] > 0) { 
                    int gap = Math.abs(currentNum - pastValue);
                    tempDP[gap] = Math.max(tempDP[gap], dp[pastValue][gap] + 1);
                }
            }
            
            // Suffix Max Sweep right-to-left
            for (int gap = 299; gap >= 0; gap--) {
                tempDP[gap] = Math.max(tempDP[gap], tempDP[gap + 1]);
            }
            
            // Merge into the global DP table
            for (int gap = 0; gap <= 300; gap++) {
                dp[currentNum][gap] = Math.max(dp[currentNum][gap], Math.max(tempDP[gap], 1));               
                maxLength = Math.max(maxLength, dp[currentNum][gap]);
            }
        }

        return maxLength;
    }
}
