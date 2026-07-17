public class StockIII {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] empty = new int[n];
        int[] empty1 = new int[n];
        int[] empty2 = new int[n];
        int[] hold1 = new int[n];
        int[] hold2 = new int[n];
        hold1[0] = -prices[0];
        hold2[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            empty[i] = empty[i - 1]; // rest
            hold1[i] = Math.max(hold1[i - 1], empty[i - 1] - prices[i]);
            empty1[i] = Math.max(empty1[i - 1], hold1[i - 1] + prices[i]);
            hold2[i] = Math.max(hold2[i - 1], empty1[i - 1] - prices[i]);
            empty2[i] = Math.max(empty2[i - 1], hold2[i - 1] + prices[i]);
        }
        return empty2[n - 1];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int empty = 0;
        int empty1 = 0;
        int empty2 = 0;
        int hold1 = -prices[0];
        int hold2 = -prices[0];
        for (int i = 1; i < n; i++) {
            int newempty = empty; // rest
            int newhold1 = Math.max(hold1, empty - prices[i]);
            int newempty1 = Math.max(empty1, hold1 + prices[i]);
            int newhold2 = Math.max(hold2, empty1 - prices[i]);
            int newempty2 = Math.max(empty2, hold2 + prices[i]);

            empty = newempty;
            hold1 = newhold1;
            empty1 = newempty1;
            hold2 = newhold2;
            empty2 = newempty2;
        }
        return empty2;
    }

    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int empty = 0;
        int empty1 = 0;
        int empty2 = 0;
        int hold1 = -prices[0];
        int hold2 = -prices[0];
        for (int i = 1; i < n; i++) {
            empty2 = Math.max(empty2, hold2 + prices[i]);
            hold2 = Math.max(hold2, empty1 - prices[i]);
            empty1 = Math.max(empty1, hold1 + prices[i]);
            hold1 = Math.max(hold1, empty - prices[i]);
        }
        return empty2;
    }

}