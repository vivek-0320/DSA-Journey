import java.util.Arrays;

public class MinCostToProcessElements {
    public int minimumCost(int[] nums, int k) {
        long powerLeft = k;
        long turboUsed = 0;
        int MOD = 1_000_000_007;

        for (int num : nums) {
            if (num > powerLeft) {
                long deficit = num - powerLeft;
                long turboRequired = (deficit + k - 1) / k;

                powerLeft += turboRequired * k;
                turboUsed += turboRequired;
            }
            powerLeft -= num;
        }

        // Calculate (T * (T + 1)) / 2 safely using modulo arithmetic
        long totalCost;
        if (turboUsed % 2 == 0) {
            totalCost = ((turboUsed / 2) % MOD * (turboUsed + 1) % MOD) % MOD;
        } else {
            totalCost = (turboUsed % MOD * ((turboUsed + 1) / 2) % MOD) % MOD;
        }

        return (int) totalCost;

    }
}
