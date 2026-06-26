import java.util.*;

public class Graph {

    public static int[][] adjMatrix(int[][] path, int n,boolean directed) {
        int[][] graph = new int[n+1][n+1];
        for (int i = 0; i < path.length; i++) {
            int u = path[i][0];
            int v = path[i][1];
            graph[u][v]=1;
            if(!directed)
                graph[v][u]=1;
        }
        return graph;
    }

    public static List<List<Integer>> adjList(int[][] path, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
            
        for (int i = 0; i < path.length; i++) {
            int u = path[i][0];
            int v = path[i][1];
            graph.get(u).add(v);
        }
        return graph;
    }
}
