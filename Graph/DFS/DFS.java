import java.util.*;

public class DFS {

    public static List<Integer> dfs(int[][] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfsHelper(graph, i, vis, list);
            }
        }
        return list;
    }

    static void dfsHelper(int[][] graph, int node, boolean[] vis, List<Integer> list) {
        vis[node] = true;
        list.add(node);
        for (int x = 0; x < graph.length; x++) {
            if (graph[node][x] == 1 && !vis[x]) {
                dfsHelper(graph, x, vis, list);
            }
        }
    }

    public static List<Integer> dfsiterative(int[][] graph) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);
        stack.push(0);
        vis[0] = true;
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            list.add(curr);
            for (int x = 0; x < n; x++) {
                if (graph[curr][x] == 1 && !vis[x]) {
                    stack.push(x);
                    vis[x] = true;
                }
            }
        }
        return list;
    }

    public static List<Integer> dfsiterative(List<List<Integer>> graph) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = graph.size();
        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);
        stack.push(0);
        vis[0] = true;
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            list.add(curr);
            for (int neighbor : graph.get(curr)) {
                if (!vis[neighbor]) {
                    stack.push(neighbor);
                    vis[neighbor] = true;
                }
            }
        }
        return list;
    }
}
