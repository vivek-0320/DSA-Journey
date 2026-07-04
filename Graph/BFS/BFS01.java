import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class BFS01 {

    public static int findCost(int r, int c, int[][] grid) {
        final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        Deque<int[]> deq = new ArrayDeque<>();
        int[][] time = new int[r][c];
        deq.add(new int[] { 0, 0 });
        for (int i = 0; i < r; i++)
            Arrays.fill(time[i], Integer.MAX_VALUE);
        time[0][0] = 0;
        while (!deq.isEmpty()) {
            int[] curr = deq.poll();
            int row = curr[0];
            int col = curr[1];
            int defaultDir = grid[row][col] - 1;

            for (int i = 0; i < 4; i++) {
                int nr = row + DIRS[i][0];
                int nc = col + DIRS[i][1];

                if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                    int cost = (i == defaultDir) ? 0 : 1;
                    
                    if (time[row][col] + cost < time[nr][nc]) {
                        time[nr][nc] = time[row][col] + cost;

                        if (cost == 0) {
                            deq.addFirst(new int[] { nr, nc });
                        } else {
                            deq.addLast(new int[] { nr, nc });
                        }
                    }
                }
            }

            // System.out.println("Time[] after " + Arrays.toString(curr));
            // for (int x = 0; x < r; x++) {
            // for (int y = 0; y < c; y++) {
            // System.out.print(time[x][y] + " ");
            // }
            // System.out.println();
            // }
        }

        return time[r - 1][c - 1];

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] grid = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        System.out.println(findCost(r, c, grid));
        sc.close();
    }

}
