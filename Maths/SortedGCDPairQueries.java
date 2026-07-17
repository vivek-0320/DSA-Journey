import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedGCDPairQueries {

    public int gcd(int a, int b) {
        return b % a == 0 ? a : gcd(b % a, a);
    }

    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        List<Integer> gcdPairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int gcd = gcd(nums[i],nums[j]);
                gcdPairs.add(gcd);
            }
        }
        Collections.sort(gcdPairs);
        int[] res = new int[queries.length];
        for(int i=0;i<queries.length;i++)
        {
            res[i] = gcdPairs.get((int)queries[i]);
        }
        return res;

    }

    public int[] gcdValuesOptimal(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Step 1: Count occurrences of every number in the input
        long[] count = new long[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        // exactGcd[i] will store the exact number of pairs whose GCD is strictly 'i'
        long[] exactGcd = new long[maxVal + 1];

        // Step 2: Work backwards from the largest possible GCD down to 1
        for (int i = maxVal; i >= 1; i--) {
            
            // Count how many numbers in the array are multiples of 'i'
            long multiplesCount = 0;
            for (int j = i; j <= maxVal; j += i) {
                multiplesCount += count[j];
            }

            // Calculate the total number of pairs we can form using these multiples
            long pairs = multiplesCount * (multiplesCount - 1) / 2;

            // Subtract the pairs that have a strictly larger GCD which is also a multiple of 'i'
            for (int j = 2 * i; j <= maxVal; j += i) {
                pairs -= exactGcd[j];
            }

            // We are left with the exact number of pairs that have a GCD of 'i'
            exactGcd[i] = pairs;
        }

        // Step 3: Create a Prefix Sum array out of our exact counts
        long[] prefix = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefix[i] = prefix[i - 1] + exactGcd[i];
        }

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            long target = queries[q];
            
            int low = 1;
            int high = maxVal;
            int result = maxVal;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefix[mid] > target) {
                    result = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            ans[q] = result;
        }

        return ans;
    }
}