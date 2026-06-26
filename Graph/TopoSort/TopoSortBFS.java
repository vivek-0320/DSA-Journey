import java.util.*;

public class TopoSortBFS {
    public List<Integer> topoSort(List<List<Integer>> graph) {
        Queue<Integer> queue = new ArrayDeque<>();
        int n = graph.size();
        int[] inDegree = new int[n];
        List<Integer> res = new ArrayList<>();
        for (var list : graph) {
            for (int node : list) {
                inDegree[node]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res.add(curr);
            for (int u : graph.get(curr)) {
                inDegree[u]--;
                if(inDegree[u] == 0) {
                    queue.add(u);
                }
            }
        }
        return res;
    }

}
