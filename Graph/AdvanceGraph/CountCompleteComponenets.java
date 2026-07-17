import java.util.*;

public class CountCompleteComponenets {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());

        for(int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        boolean[] vis = new boolean[n];
        Deque<Integer> deq  = new ArrayDeque<>();
        int comppleteComponents = 0;
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                vis[i] = true;
                deq.add(i);
                int edgeCount = 0;
                int nodeCount = 0;
                while(!deq.isEmpty())
                {
                    int curr = deq.poll();
                    edgeCount = graph.get(curr).size();
                    nodeCount++;

                    for(int v : graph.get(curr))
                    {
                        if(!vis[v])
                            deq.add(v);
                    }
                }
                if(edgeCount/2 == (nodeCount*(nodeCount-1)/2))
                    comppleteComponents++;
            }
        }
        return comppleteComponents;
    }
}
