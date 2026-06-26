import java.util.*;

public class CycleCheckDFS {
    public boolean checkCycleIterative(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] vis = new boolean[n];
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                stack.push(new int[] { i, -1 });
                while (!stack.isEmpty()) {
                    int[] pair = stack.pop();
                    int node = pair[0];
                    int parent = pair[1];
                    vis[node] = true;
                    for (int u : graph.get(node)) {
                        if (!vis[u]) {
                            stack.push(new int[] { u, node });
                        } else if (u != parent) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCycle(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                if (dfs(graph, i, -1, vis))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(List<List<Integer>> graph, int node, int parent, boolean[] vis) {
        vis[node] = true;
        for (int u : graph.get(node)) {
            if (!vis[u]) {
                if (dfs(graph, u, node, vis))
                    return true;
            } else if (u != parent) {
                return true;
            }
        }
        return false;
    }
}
