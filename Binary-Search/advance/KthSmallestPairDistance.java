import java.util.Arrays;

public class KthSmallestPairDistance {

    public boolean countPairs(int[] nums, int mid, int k) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while(nums[right] - nums[left] > mid)
                left++;
            count += (right - left);
        }
        return count >= k;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = -1;
        int high = nums[nums.length - 1] - nums[0];

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(nums, mid, k))
                high = mid;
            else
                low = mid;
        }
        return high;
    }
}