import java.util.*;

public class BFS {
    public static List<Integer> bfs(int[][] graph) {
        int n = graph.length;
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.add(0);
        used[0] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            list.add(current);
            for (int x = 0; x < n; x++) {
                if (graph[current][x] == 1 && !used[x]) {
                    queue.add(x);
                    used[x] = true;
                }
            }
        }
        return list;
    }

    public static List<Integer> bfs(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] used = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        queue.add(0);
        used[0] = true;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            list.add(current);

            for (int neighbor : graph.get(current)) {
                if (!used[neighbor]) {
                    queue.add(neighbor);
                    used[neighbor] = true;
                }
            }
        }
        return list;
    }

}
