import java.util.*;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> freq = new TreeMap<>();
        int maxNum = 0;
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            maxNum = Math.max(num,maxNum);
        }
        int[] dp = new int[maxNum+1];
        dp[0] = 0;
        dp[1] = freq.getOrDefault(1,0);
        for(int i=2;i<=maxNum;i++)
        {
            dp[i] = Math.max(dp[i-1],dp[i-2]+i*freq.getOrDefault(i, 0));
        }
        return dp[maxNum];
    }

}
