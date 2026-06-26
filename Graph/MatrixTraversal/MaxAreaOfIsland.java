import java.util.ArrayDeque;
import java.util.Deque;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> stack = new ArrayDeque<>();
        int maxArea = Integer.MIN_VALUE;
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int area = 0;
                if (grid[i][j] == 1) {
                    stack.push(new int[] { i, j });
                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        int x = curr[0], y = curr[1];
                        if (grid[x][y] == 0) continue;
                        grid[x][y] = 0;
                        area++;
                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + x;
                            int ny = dy[k] + y;
                            if (nx > -1 && nx < m && ny > -1 && ny < n && grid[nx][ny] == 1) {
                                stack.push(new int[] { nx, ny });
                            }
                        }
                    }
                }
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
