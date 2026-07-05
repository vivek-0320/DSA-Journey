import java.util.*;

// This is solved using multiSource BFS  + modified Dijkstra to find Path.
// First use BFS to make a new grid of safenessFactor by processing the thieves layer by layer.
// then use dijkstra which uses max heap, to always choose maxSafety factor for path , 
// and also keep track of the globalMin safety factor encountered so far for answer.
// 

public class SafenessFactor {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int r = grid.size();
        int c = grid.get(0).size();

        if (grid.get(0).get(0) == 1 || grid.get(r - 1).get(c - 1) == 1) {
            return 0;
        }

        int[][] safeGrid = new int[r][c];
        Deque<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            Arrays.fill(safeGrid[i], Integer.MAX_VALUE);
            for (int j = 0; j < c; j++) {
                if (grid.get(i).get(j) == 1) {
                    safeGrid[i][j] = 0;
                    que.add(new int[] { i, j, 0 });
                }
            }
        }

        final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!que.isEmpty()) {
            int[] curr = que.poll();
            int row = curr[0];
            int col = curr[1];
            int safety = curr[2];

            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c) {
                    if (safeGrid[newRow][newCol] == Integer.MAX_VALUE) {
                        safeGrid[newRow][newCol] = safety + 1;
                        que.add(new int[] { newRow, newCol, safety + 1 });
                    }
                }
            }
        }

        // Phase 2: Max-Heap Dijkstra to find the path with the maximum bottleneck
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        boolean[][] vis = new boolean[r][c];

        pq.add(new int[] { safeGrid[0][0], 0, 0 });
        vis[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currMin = curr[0];
            int row = curr[1];
            int col = curr[2];

            if (row == r - 1 && col == c - 1)
                return currMin;

            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c && !vis[newRow][newCol]) {
                    vis[newRow][newCol] = true;
                    pq.add(new int[] { Math.min(currMin, safeGrid[newRow][newCol]), newRow, newCol });
                }
            }
        }

        return 0;
    }

}