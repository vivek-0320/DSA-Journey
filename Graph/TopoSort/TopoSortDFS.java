import java.util.*;

public class TopoSortDFS {
    public static LinkedList<Integer> topoSort(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] vis = new boolean[n];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(graph, i, vis, list);
            }
        }
        return list;
    }

    public static void dfs(List<List<Integer>> graph, int node, boolean[] vis, LinkedList<Integer> list) {
        vis[node] = true;
        for (int u : graph.get(node)) {
            if (!vis[u]) {
                dfs(graph, u, vis, list);
            }
        }
        list.addFirst(node);
    }

    public static LinkedList<Integer> topoSortIterative(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] vis = new boolean[n];
        Deque<int[]> stack = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                stack.push(new int[] { i, 0 });
                while (!stack.isEmpty()) {
                    int[] curr = stack.pop();
                    int node = curr[0];
                    int processed = curr[1];

                    if (processed == 1) {
                        list.addFirst(node);
                        continue;
                    }

                    if (!vis[node]) {
                        vis[node] = true;
                        stack.push(new int[] { node, 1 });
                        for (int u : graph.get(node)) {
                            if (!vis[u]) {
                                stack.push(new int[] { u, 0 });
                            }
                        }
                    }

                }
            }
        }
        return list;
    }

}
