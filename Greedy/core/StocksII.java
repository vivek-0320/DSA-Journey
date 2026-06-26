public class StocksII {
  public int maxProfit(int[] prices) {
        int totalProfit = 0;
        int holdPrice = prices[0];
        for(int i = 1 ; i < prices.length ; i++ ) {
            if(holdPrice > prices[i]) {
                holdPrice = prices[i];
            } else {
                totalProfit += prices[i] - holdPrice;
                holdPrice = prices[i];
            }
        }
        return totalProfit;
    }
}
