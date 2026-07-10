import java.util.PriorityQueue;

public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;
        pq.add(new int[] { grid[0][0], 0, 0 }); // {maxTimeSeen,r,c}
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int maxTimeSeen = curr[0];
            int r = curr[1];
            int c = curr[2];

            // System.out.println(Arrays.toString(curr));

            if (r == n - 1 && c == n - 1)
                return maxTimeSeen;

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    pq.add(new int[] { Math.max(grid[nr][nc], maxTimeSeen), nr, nc });
                }
            }
        }
        return -1;
    }
}
