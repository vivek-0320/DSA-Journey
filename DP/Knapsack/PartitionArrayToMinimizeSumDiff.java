import java.util.*;

public class PartitionArrayToMinimizeSumDiff {

    // This question feels similar to Last Stone Weight II, but here the trap is the split of array has 
    // to be size of N. Since the array len is 30, generating all combinations 2^30 will cross 1 billion operations.
    // But Meet-In-Middle Approach lets us create two universe of 2^15 (roughly 32k operationns) and then stitch it back together 
    // using Two pointer knapsack.
    // First we divide the array into two half.
    // Then we generate bucket sums , i.e. if we chose 1 element from array, then sum of all combinations ... till N for both halves.
    // After generating the buckets, we use two pointer to get close to half of the total of array.
    // IF N = 3, for each i :
    // we try to get to half sum by picking elements from left bucket (i) + right bucket(n-i).
    // we place left pointer at start of leftBucket, and right pointer to end of rightBucket.
    // If left[l]+right[r] <= halfSum, then we update our bestSum and increment left pointer.
    // else if left[l]+right[r] > halfSum, we decrement right pointer to find lower sum.
    // After full iteration, result = total - 2* bestSum;

    private void dfs(int[] nums, int idx, int end, int count, int sum, List<Integer>[] buckets) {
        if (idx == end) {
            buckets[count].add(sum);
            return;
        }

        // Choice 1: Include the current number
        dfs(nums, idx + 1, end, count + 1, sum + nums[idx], buckets);

        // Choice 2: Skip the current number
        dfs(nums, idx + 1, end, count, sum, buckets);
    }

    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        ArrayList<Integer>[] leftBuckets = new ArrayList[n + 1];
        ArrayList<Integer>[] rightBuckets = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            leftBuckets[i] = new ArrayList<>();
            rightBuckets[i] = new ArrayList<>();
        }
        dfs(nums, 0, n, 0, 0, leftBuckets);
        dfs(nums, n, nums.length, 0, 0, rightBuckets);

        int total = 0;
        for (int num : nums)
            total += num;

        int bestSum = Integer.MIN_VALUE / 2;
        int half = total / 2;

        for (int i = 0; i <= n; i++) {
            List<Integer> left = leftBuckets[i];
            List<Integer> right = rightBuckets[n - i];
            Collections.sort(left);
            Collections.sort(right);
            int l = 0;
            int r = right.size() - 1;
            while (l < left.size() && r > 0) {
                int sum = left.get(l) + right.get(r);
                if (sum <= half) {
                    bestSum = Math.max(bestSum, sum);
                    l++;
                } else {
                    r--;
                }
            }
        }
        return Math.abs(total - (2 * bestSum));
    }
}
