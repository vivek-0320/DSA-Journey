public class SubarrayWithKDistinctInt {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        // Since nums[i] <= nums.length, an array is much faster than a HashMap
        int[] freq = new int[nums.length + 1];
        int uniqueCount = 0;
        int left = 0;
        int subarrays = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            if (freq[num] == 0) {
                uniqueCount++;
            }
            freq[num]++;
            while (left <= right && uniqueCount > k) {
                int del = nums[left];
                freq[del]--;
                if (freq[del] == 0) {
                    uniqueCount--;
                }
                left++;
            }
            subarrays += (right - left + 1);
        }

        return subarrays;
    }
}