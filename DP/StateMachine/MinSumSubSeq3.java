import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class MinSumSubSeq3 {

    public int dfs(int i, int k, int prevIdx, int[] nums, Integer[][][] memo) {
        if (k == 0)
            return 0;
        if (i == nums.length)
            return Integer.MAX_VALUE;

        if (memo[i][prevIdx][k] != null)
            return memo[i][prevIdx][k];

        int skip = dfs(i + 1, k, prevIdx, nums, memo);
        int take = Integer.MAX_VALUE;

        if (prevIdx == -1 || (k == 2 && nums[i] > nums[prevIdx]) || (k == 1 && nums[i] < nums[prevIdx])) {
            take = dfs(i + 1, k - 1, i, nums, memo);
            if (take != Integer.MAX_VALUE)
                take += nums[i];
        }
        return memo[i][prevIdx][k] = Math.min(skip, take);
    }

    public int solveMemo(int[] nums) {
        int n = nums.length;
        Integer[][][] memo = new Integer[n][n][4];
        return dfs(0, 3, -1, nums, memo);
    }

    public int solveOptimal(int[] nums) {
        int n = nums.length;
        int[] minL = new int[n];
        int[] minR = new int[n];

        Arrays.fill(minL, Integer.MAX_VALUE);
        Arrays.fill(minR, Integer.MAX_VALUE);

        int currMin = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < currMin)
                currMin = nums[i];

            if (currMin < nums[i])
                minL[i] = currMin;
        }
        currMin = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < currMin)
                currMin = nums[i];

            if (currMin < nums[i])
                minR[i] = currMin;
        }

        System.out.println(Arrays.toString(minL));
        System.out.println(Arrays.toString(minR));

        int minAns = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (minL[i] != Integer.MAX_VALUE && minR[i] != Integer.MAX_VALUE)
                minAns = Math.min(nums[i] + minL[i] + minR[i], minAns);
        }
        return minAns;
    }

    public int minSum(int[] nums) {
        return solveOptimal(nums);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        MinSumSubSeq3 ob = new MinSumSubSeq3();
        int sum = ob.minSum(nums);
        System.out.println(sum);
    }
}