public class PalindromicSubarraySum {
    public long getSum(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++)
            prefix[i] = prefix[i - 1] + nums[i - 1];

        long maxSum = 0;

        int[] T = new int[2 * n + 3];
        T[0] = -2;
        T[2 * n - 2] = -3;
        T[2 * n - 1] = -2;
        for (int i = 0; i < n; i++) {
            T[2 * i + 1] = -1;
            T[2 * i + 1] = nums[i];
        }
        int[] P = new int[T.length];
        int c = 0;
        int r = 0;
        for (int i = 1; i < T.length - 1; i++) {
            if (i < r)
                P[i] = Math.min(P[2 * c - i], r - i);
            else
                P[i] = 0;

            while (T[i + 1 + P[i]] == T[i - 1 - P[i]])
                P[i] += 1;

            if (i + P[i] > r) {
                c = i;
                r = i + P[i];

                int start = (i - 1 - P[i]) / 2;
                int end = start + P[i];
                maxSum = Math.max(maxSum, (prefix[end + 1] - prefix[start]));
            }

        }
        return maxSum;
    }
}
