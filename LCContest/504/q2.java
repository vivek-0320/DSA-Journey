import java.util.*;

class q2 {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] bonuses = new int[n];
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }
            bonuses[i] = count;
        }
        int[] dp = new int[budget + 1];
        
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstBuyValue = 1 + bonuses[i];
            
            for (int w = budget; w >= price; w--) {
                dp[w] = Math.max(dp[w], dp[w - price] + firstBuyValue);
            }
            for (int w = price; w <= budget; w++) {
                dp[w] = Math.max(dp[w], dp[w - price] + 1);
            }
        }
        
        int maxItems = 0;
        for (int w = 0; w <= budget; w++) {
            maxItems = Math.max(maxItems, dp[w]);
        }
        
        return maxItems;
    }
}