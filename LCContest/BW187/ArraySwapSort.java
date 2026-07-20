package LCContest.BW187;

public class ArraySwapSort {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        long swaps = 0;
        int count0 = 0;
        int count1 = 0;
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < a) {
                count0++;
            } else if (nums[i] >= a && nums[i] <= b) {
                swaps += count0;
                count1++;
            } else {
                swaps += (count0 + count1);
            }
        }
        
        return (int) (swaps % 1_000_000_007);
    }
}
