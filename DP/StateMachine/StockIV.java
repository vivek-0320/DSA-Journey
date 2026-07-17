public class StockIV {

    public int maxProfit(int k, int[] prices) {
        int[] hold = new int[k + 1];
        int[] empty = new int[k + 1];
        for (int i = 1; i <= k; i++)
            hold[i] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            // Iterate backwards to prevent state bleeding
            for (int j = k; j > 0; j--) {
                // Sell: transition from holding in the current transaction (j)
                empty[j] = Math.max(empty[j], hold[j] + price);

                // Buy: transition from empty in the previous transaction (j-1)
                hold[j] = Math.max(hold[j], empty[j - 1] - price);
            }
        }
        return empty[k];
    }
}