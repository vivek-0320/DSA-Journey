import java.util.Arrays;
import java.util.List;

public class PathWithMaxScore {
    public int[] pathsWithMaxScore(List<String> board) {
        int r = board.size();
        int c = board.get(0).length();
        int MOD = 1_000_000_007;

        int[][] maxDP = new int[r + 1][c + 1];
        int[][] waysDP = new int[r + 1][c + 1];

        for (int i = 0; i <= r; i++) {
            Arrays.fill(maxDP[i], -1);
        }

        maxDP[r - 1][c - 1] = 0;
        waysDP[r - 1][c - 1] = 1;

        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                char ch = board.get(i).charAt(j);

                if (ch == 'S' || ch == 'X') {
                    continue;
                }

                // Gather scores from the 3 valid directions
                int right = maxDP[i][j + 1];
                int down = maxDP[i + 1][j];
                int diag = maxDP[i + 1][j + 1];

                // Find the absolute highest score among neighbors
                int maxNeighbor = Math.max(right, Math.max(down, diag));

                // 4. If at least one neighbor is reachable, process this cell
                if (maxNeighbor != -1) {
                    int currentScore = (ch == 'E') ? 0 : (ch - '0');
                    maxDP[i][j] = maxNeighbor + currentScore;

                    // 5. The Tie-Breaker: Only sum up ways from optimal paths
                    if (right == maxNeighbor) {
                        waysDP[i][j] = (waysDP[i][j] + waysDP[i][j + 1]) % MOD;
                    }
                    if (down == maxNeighbor) {
                        waysDP[i][j] = (waysDP[i][j] + waysDP[i + 1][j]) % MOD;
                    }
                    if (diag == maxNeighbor) {
                        waysDP[i][j] = (waysDP[i][j] + waysDP[i + 1][j + 1]) % MOD;
                    }
                }
            }
        }
        if (maxDP[0][0] == -1) {
            return new int[] { 0, 0 };
        }

        return new int[] { maxDP[0][0], waysDP[0][0] };
    }
}
