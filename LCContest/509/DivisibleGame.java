import java.util.Set;
import java.util.TreeSet;

public class DivisibleGame {

    public int divisibleGame(int[] nums) {
        Set<Integer> possibleK = new TreeSet<>();
        for (int n : nums) {
            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    if (i > 1)
                        possibleK.add(i);
                    if (n / i > 1)
                        possibleK.add(n / i);
                }
            }
        }
        if (possibleK.isEmpty()) {
            possibleK.add(2);
        }

        long maxDiff = Long.MIN_VALUE;
        long bestK = 2;
        for (int k : possibleK) {
            long currentDiff = evaluate(nums, k);

            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
                bestK = k;
            } else if (currentDiff == maxDiff && k < bestK)
                bestK = k;
        }
        long MOD = 1_000_000_007;
        long prod = (maxDiff % MOD) * (bestK % MOD) % MOD;
        if(prod < 0)
            prod = (prod+MOD) % MOD;

        return (int)prod;
    }

    private long evaluate(int[] nums, int k) {
        long maxSum = Long.MIN_VALUE;
        long currentSum = 0;

        for (int x : nums) {
            long val = (x % k == 0) ? x : -x;
            currentSum = Math.max(val, currentSum + val);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
