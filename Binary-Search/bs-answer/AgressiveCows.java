import java.util.Arrays;

public class AgressiveCows {

    public boolean isPossible(long mid, int[] nums, int k) {
        int cowsPlaced = 1;
        int lastPos = nums[0];
        System.out.printf("for mid  = %d\n", mid);
        for (int i = 1; i < nums.length; i++) {
            if (lastPos + mid <= nums[i]) {
                cowsPlaced++;
                lastPos = nums[i];
                System.out.printf("Cow %d placed at %d\n", cowsPlaced, lastPos);
            }
        }
        return cowsPlaced >= k;
    }

    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);
        long low = 0;
        long high = nums[nums.length - 1] - nums[0];
        while (low + 1 < high) {
            long mid = low + (high - low) / 2;
            if (isPossible(mid, nums, k))
                low = mid;
            else
                high = mid;
        }
        return (int) low;
    }
}