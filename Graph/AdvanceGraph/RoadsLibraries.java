import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RoadsLibraries {
    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (List<Integer> city : cities) {
            int u = city.get(0);
            int v = city.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        long costOfLibraryAtEachCity = n*c_lib;
        long costOfLibraryAtSomeCity = 0L;

        boolean[] vis = new boolean[n+1];
        Deque<Integer> deq = new ArrayDeque<>();
        for(int i=1;i<=n;i++)
        {
            if(!vis[i])
            {
                vis[i] = true;
                deq.add(i);
                int egdeCount = 0;
                int nodeCount = 0;
                while(!deq.isEmpty())
                {
                    int u = deq.poll();
                    nodeCount++;
                    
                    for(Integer v : graph.get(u))
                    {
                        if(!vis[v])
                        {
                            egdeCount++;
                            vis[v] = true;
                            deq.add(v);
                        }
                    }
                }
                costOfLibraryAtSomeCity += nodeCount == 1 ? c_road  : (egdeCount*c_road);
            }
        }
        return Math.min(costOfLibraryAtSomeCity,costOfLibraryAtEachCity);
    }
}
