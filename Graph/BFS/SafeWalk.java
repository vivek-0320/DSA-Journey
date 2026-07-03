import java.util.*;

public class SafeWalk {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int r = grid.size();
        int c = grid.get(0).size();

        int initialHealth = grid.get(0).get(0) == 1 ? health - 1 : health;
        if (initialHealth <= 0)
            return false;

        boolean[][][] visited = new boolean[r][c][health + 1];
        Deque<int[]> deq = new ArrayDeque<>();
        final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        deq.add(new int[] { 0, 0, initialHealth });
        visited[0][0][initialHealth] = true;
        while (!deq.isEmpty()) {
            int[] curr = deq.pollFirst();
            int row = curr[0];
            int col = curr[1];
            int healthLeft = curr[2];

            if (row == r - 1 && col == c - 1)
                return true;

            for (int[] dir : DIRS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c) {

                    int cost = grid.get(newRow).get(newCol);
                    int newHealth = healthLeft - cost;

                    // Ensure health is STRICTLY POSITIVE (> 0) and state is unvisited
                    if (newHealth > 0 && !visited[newRow][newCol][newHealth]) {
                        visited[newRow][newCol][newHealth] = true;

                        // 0-1 BFS logic
                        if (cost == 0) {
                            deq.addFirst(new int[] { newRow, newCol, newHealth });
                        } else {
                            deq.addLast(new int[] { newRow, newCol, newHealth });
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SafeWalk ob = new SafeWalk();

        int health = 1;
        System.out.println(ob.findSafeWalk(null, 0));
    }
}
