import java.util.*;

public class TownJudge {
    public int findJudge(int n, int[][] trust) {
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < trust.length; i++) {
            int u = trust[i][0];
            int v = trust[i][1];
            graph[u][v] = 1;
        }
        HashSet<Integer> vis = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (!vis.contains(i)) {
                stack.push(i);
                vis.add(i);
                while (!stack.isEmpty()) {
                    int curr = stack.pop();
                    boolean flag = true;
                    for (int x = 1; x <= n; x++) {
                        if (graph[curr][x] == 1) {
                            // found an outgoing edge (regardless of visited)
                            flag = false;
                            if (!vis.contains(x)) {
                                stack.push(x);
                                vis.add(x);
                            }
                        }
                    }
                    if (flag) {
                        for (int x = 1; x <= n; x++) {
                            if (x != curr && graph[x][curr] == 1) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag)
                        return curr;
                }
            }
        }
        return -1;
    }
    public int findJudgeOptimal(int n, int[][] trust) {
        int[] out = new int[n+1];
        int[] in = new int[n+1];
        for (int i = 0; i < trust.length; i++) {
            int u = trust[i][0];
            int v = trust[i][1];
            out[u]++;
            in[v]++;
        }
        for(int i=1;i<=n;i++) {
            if(out[i]==0 && in[i]==n-1)
                return i;
        }
        return -1;
    }
}
