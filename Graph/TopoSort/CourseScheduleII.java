import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            adj.get(u).add(v);
            inDegree[v]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }
        int idx = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[idx++] = node;
            for (int u : adj.get(node)) {
                inDegree[u]--;
                if (inDegree[u] == 0)
                    queue.add(u);
            }
        }
        return idx < numCourses ? new int[] {} : res;
    }
}
