import java.util.HashMap;

public class BinarySubarrayWithSums {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i <= n; i++) {
            int target = prefixSum[i] - goal;
            if (map.containsKey(target)) {
                count += map.get(target);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }

    public int numSubarraysWithSum2(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    public int atMost(int[] nums, int goal) {
        int count = 0;
        int left = 0, right = 0, sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while(sum > goal) {
                sum-=nums[left];
                left++;
            }
            count+=(right - left + 1);
            right++;
        }
        return count;
    }
}
