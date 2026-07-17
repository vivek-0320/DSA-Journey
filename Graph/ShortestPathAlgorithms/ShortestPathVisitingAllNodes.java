import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        boolean[][] vis = new boolean[n][(1 << n)];
        Deque<int[]> deq = new ArrayDeque<>(); // {node,steps,bitmask}
        int bitmask = 0;
        for (int i = 0; i < n; i++) {
            // System.out.printf("%d , %d , %d\n", i, 0, (bitmask | 1 << i));
            int newMask = bitmask | 1 << i;
            vis[i][newMask] = true;
            deq.add(new int[] { i, 0, newMask });
        }
        while(!deq.isEmpty())
        {
            int[] curr = deq.poll();
            int u = curr[0];
            int steps = curr[1];
            int currMask = curr[2];

            if(currMask == (1 << n)-1)
                return steps;
            
            for(int v : graph[u])
            {
                int newMask = currMask | (1 << v);
                if(!vis[v][newMask])
                {
                    vis[v][newMask] = true;
                    deq.add(new int[]{v,steps+1,newMask});
                }
            }
        }
        return 0;
    }
}
