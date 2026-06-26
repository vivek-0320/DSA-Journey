public class CountNiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int oddCount = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] % 2 == 1) {
                oddCount++;
            }
            while (oddCount > k) {
                count ++;
                if (nums[left] % 2 == 1)
                    oddCount--;
                left++;
            }
            right++;
        }
        return count;
    }
}
