public class MaximumPulse {
    public long maxPulse(int[] nums) {
        int n = nums.length;
        long totalPulse = 0;
        
        for (int i = 0; i < n; i++) {
            totalPulse += getC(nums, i);
        }
        
        long minEvenSubarraySum = 0;
        
        long currentMin = 0;
        for (int i = 0; i + 1 < n; i += 2) {
            long pairSum = getC(nums, i) + getC(nums, i + 1);
            currentMin = Math.min(pairSum, currentMin + pairSum);
            minEvenSubarraySum = Math.min(minEvenSubarraySum, currentMin);
        }
        
        currentMin = 0;
        for (int i = 1; i + 1 < n; i += 2) {
            long pairSum = getC(nums, i) + getC(nums, i + 1);
            currentMin = Math.min(pairSum, currentMin + pairSum);
            minEvenSubarraySum = Math.min(minEvenSubarraySum, currentMin);
        }
        
        return totalPulse - 2 * minEvenSubarraySum;
    }
    
    private long getC(int[] nums, int i) {
        return (i % 2 == 0) ? nums[i] : -nums[i];
    }
}