import java.util.*;

public class FireEscape {
    public static int findPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] fireTime = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }

        Queue<int[]> fireQ = new LinkedList<>();
        int sr = -1, sc = -1, er = -1, ec = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'F') {
                    fireQ.add(new int[] { i, j });
                    fireTime[i][j] = 0; // Fire is here at minute 0
                } else if (grid[i][j] == 'S') {
                    sr = i;
                    sc = j;
                } else if (grid[i][j] == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }

        int[][] DIRS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        // 2. Pre-compute fire arrival times (Multi-source BFS)
        int time = 0;
        while (!fireQ.isEmpty()) {
            int size = fireQ.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] curr = fireQ.poll();
                for (int[] dir : DIRS) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    
                    // Fire spreads to valid, non-wall cells
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#') {
                        if (fireTime[nr][nc] == Integer.MAX_VALUE) {
                            fireTime[nr][nc] = time;
                            fireQ.add(new int[] { nr, nc });
                        }
                    }
                }
            }
        }

        // 3. Navigate the player (Single-source BFS)
        Queue<int[]> playerQ = new LinkedList<>();
        playerQ.add(new int[] { sr, sc, 0 });
        boolean[][] vis = new boolean[n][m];
        vis[sr][sc] = true;

        while (!playerQ.isEmpty()) {
            int[] curr = playerQ.poll();
            int r = curr[0];
            int c = curr[1];
            int steps = curr[2];

            if (r == er && c == ec) {
                return steps;
            }

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Player moves if within bounds, not a wall, and unvisited
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '#' && !vis[nr][nc]) {
                    // CRITICAL CHECK: Does player arrive BEFORE the fire?
                    if (steps + 1 < fireTime[nr][nc]) {
                        vis[nr][nc] = true;
                        playerQ.add(new int[] { nr, nc, steps + 1 });
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        
        
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }
        
        System.out.println(findPath(grid));
        sc.close();
    }
}