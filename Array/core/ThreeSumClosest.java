import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestDiff = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int left = i+1;
            int right = n-1;
            while(left < right)
            {
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target - sum) < closestDiff)
                {
                    closestDiff = Math.abs(target-sum);
                    ans = sum;
                }
                if(sum < target)
                    left++;
                else
                    right--;
            }
        }
        return ans;
    }
}
