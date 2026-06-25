public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        // 1. Base Case: The Princess's Cell
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        // 2. Initialize the bottom row (can only move right)
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }

        // 3. Initialize the rightmost column (can only move down)
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }

        // 4. Backward DP Engine
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // Find the easiest path forward (minimum health required)
                int minHealthNeeded = Math.min(dp[i + 1][j], dp[i][j + 1]);

                // Subtract current cell's value, and ensure we never drop below 1 HP
                dp[i][j] = Math.max(1, minHealthNeeded - dungeon[i][j]);
            }
        }

        // The answer is the minimum health needed before stepping onto the starting
        // cell
        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonGame ob = new DungeonGame();
        int[][] a = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
        System.out.println(ob.calculateMinimumHP(a));

    }
}
