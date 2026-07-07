public class LastStoneWeightII {

    // Treat this as dividing the array into two parts so that their diff is minimized.
    // Pick elements in a bag of sum/2. 

    public int lastStoneWeight(int[] stones) {
        int sum = 0;
        int n = stones.length;
        for (int stone : stones)
            sum += stone;
        int[] dp = new int[(sum / 2) + 1];
        for (int i = 0; i < n; i++) {
            for (int j = sum / 2; j >= stones[i]; j--) {
                int skip = dp[j];
                int take = dp[j - stones[i]] + stones[i];
                dp[j] = Math.max(take, skip);
            }
        }
        return (sum) - (2 * dp[sum / 2]);
    }
}
