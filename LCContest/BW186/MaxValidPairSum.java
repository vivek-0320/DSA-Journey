
class MaxValidPairSum {

    // To avoid bottlneck of O(n^2), keep a track of maxI Value from left side, and update it if a valid i is there.

    public int maxValidPairSum(int[] nums, int k) {
        int maxVal = 0;
        int n = nums.length;
        int maxIVal = Integer.MIN_VALUE;
        for (int j = k; j < n; j++) {
            maxIVal = Math.max(maxIVal, nums[j - k]);
            maxVal = Math.max(maxVal, maxIVal + nums[j]);
        }
        return maxVal;
    }

}