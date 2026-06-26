import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int a = 0; a < n - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;

            if ((long) nums[a] + nums[a+1] + nums[a+2] + nums[a+3] > target) break;
            if ((long) nums[a] + nums[n-3] + nums[n-2] + nums[n-1] < target) continue;

            for (int b = a + 1; b < n - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;

                long sum2 = (long) target - nums[a] - nums[b];
                int left = b + 1, right = n - 1;

                while (left < right) {
                    int twoSum = nums[left] + nums[right];
                    if (twoSum == sum2) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (twoSum < sum2) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
