import java.util.Scanner;

public class MaxAverageSubarrayII {

    public static boolean isPossible(double mid, int[] nums, int k) {
        double sum = 0;
        double prevSum = 0;
        double minPrevSum = 0;

        for (int i = 0; i < k; i++) {
            sum += (nums[i] - mid);
        }
        if (sum >= 0)
            return true;

        for (int i = k; i < nums.length; i++) {
            sum += (nums[i] - mid);
            prevSum += (nums[i - k] - mid);

            minPrevSum = Math.min(minPrevSum, prevSum);

            if (sum - minPrevSum >= 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }
        while (high - low >= Math.pow(10, -5)) {
            double mid = low + (high - low) / 2;
            if (isPossible(mid, nums, k))
                high = mid;
            else
                low = mid;
        }
        System.out.printf("%.5f\n", low);
    }
}
