import java.util.Arrays;

public class ArrayReductionChubb {
    public int minLen(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int i = 0;
        int j = n / 2;
        int del = 0;

        // i can only go up to the midpoint, j can go to the end
        while (i < n / 2 && j < n) {
            // Using * 2L prevents potential integer overflow for massive array values
            if (nums[i] * 2L <= nums[j]) {
                del++;
                i++;
                j++;
            } else {
                // j wasn't large enough to cover 2 * nums[i], so move j forward
                j++;
            }
        }

        return n - del;
    }
}