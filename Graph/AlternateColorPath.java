import java.util.*;

public class AlternateColorPath {

    private static final int RED = 0;
    private static final int BLUE = 1;

    public int[] shortestAlternatingPathsOptimal(int n, int[][] redEdges, int[][] blueEdges) {
        // Using an array of Lists to represent the two different colored graphs
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] edge : redEdges)
            redGraph[edge[0]].add(edge[1]);
        for (int[] edge : blueEdges)
            blueGraph[edge[0]].add(edge[1]);

        // Initialize BFS Requirements
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // visited[node][color] -> ensures we don't visit the same node
        // with the same incoming edge color twice.
        boolean[][] visited = new boolean[n][2];

        // Queue stores: {current_node, current_steps, last_edge_color}
        Deque<int[]> queue = new ArrayDeque<>();

        // We can start by taking either a Red or a Blue edge from node 0
        queue.offer(new int[] { 0, 0, RED });
        queue.offer(new int[] { 0, 0, BLUE });

        // Mark both "starting colors" as visited for node 0
        visited[0][RED] = true;
        visited[0][BLUE] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int steps = curr[1];
            int lastColor = curr[2];

            // Update result: the first time we reach a node, it's the shortest path
            if (result[node] == -1) {
                result[node] = steps;
            } else {
                result[node] = Math.min(result[node], steps);
            }

            if (lastColor == RED) {
                // Last was Red, must take Blue now
                explore(node, steps, BLUE, blueGraph, visited, queue);
            } else {
                // Last was Blue, must take Red now
                explore(node, steps, RED, redGraph, visited, queue);
            }
        }
        return result;
    }

    private void explore(int u, int steps, int nextColor, List<Integer>[] graph, boolean[][] visited,
            Deque<int[]> queue) {
        for (int v : graph[u]) {
            if (!visited[v][nextColor]) {
                visited[v][nextColor] = true;
                queue.offer(new int[] { v, steps + 1, nextColor });
            }
        }
    }

    public int[] bfs(ArrayList<ArrayList<Integer>> redGraph, ArrayList<ArrayList<Integer>> blueGraph) {
        Deque<int[]> deq = new ArrayDeque<>(); // {node,cost, color}
        deq.offer(new int[] { 0, 0, 0 }); // 0 -> red
        deq.offer(new int[] { 0, 0, 1 }); // 1 -> blue

        int[] dist = new int[redGraph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean[][] vis = new boolean[redGraph.size()][2]; // {x' , x"}

        while (!deq.isEmpty()) {
            int[] curr = deq.poll();
            int u = curr[0], cost = curr[1], color = curr[2];
            dist[u] = Math.min(dist[u], cost);

            System.out.print(color == 0 ? u + " Red : " : u + " Blue: ");
            if (color == 0) // if Red was last path color , then explore Blue Graph
            {
                for (int neighbor : blueGraph.get(u)) {
                    if (!vis[neighbor][0]) {
                        deq.offer(new int[] { neighbor, cost + 1, 1 });
                        vis[neighbor][0] = true;
                        System.out.print(" " + neighbor + " ");
                    }
                }
            } else // if blue was last path color, then explore Red Graph
            {
                for (int neighbor : redGraph.get(u)) {
                    if (!vis[neighbor][1]) {
                        deq.offer(new int[] { neighbor, cost + 1, 0 });
                        vis[neighbor][1] = true;
                        System.out.print(" " + neighbor + " ");
                    }
                }
            }
            System.out.println();
        }
        return dist;
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        ArrayList<ArrayList<Integer>> redGraph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> blueGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for (int[] edge : redEdges) {
            int u = edge[0], v = edge[1];
            redGraph.get(u).add(v);
        }
        for (int[] edge : blueEdges) {
            int u = edge[0], v = edge[1];
            blueGraph.get(u).add(v);
        }
        int[] res = bfs(redGraph, blueGraph);
        for (int i = 0; i < n; i++)
            if (res[i] == Integer.MAX_VALUE)
                res[i] = -1;
        return res;
    }

    public int[] bfs2(ArrayList<ArrayList<Integer>> redGraph, ArrayList<ArrayList<Integer>> blueGraph,
            boolean turnRed) {
        Deque<int[]> deq = new ArrayDeque<>(); // {node,cost}
        deq.offer(new int[] { 0, 0 });
        int[] dist = new int[redGraph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[][] vis = new boolean[redGraph.size()][2]; // {x' , x"}
        for (int i = 0; i < redGraph.size(); i++) {
            vis[i][0] = false;
            vis[i][1] = false;
        }
        vis[0][1] = true;
        dist[0] = 0;
        while (!deq.isEmpty()) {
            int[] curr = deq.poll();
            int u = curr[0], cost = curr[1];
            dist[u] = Math.min(dist[u], cost);
            if (turnRed) // if turnRed == true, then explore Blue Graph
            {
                turnRed = false;
                for (int neighbor : blueGraph.get(u)) {
                    if (!vis[neighbor][0]) {
                        deq.offer(new int[] { neighbor, cost + 1 });
                        vis[neighbor][0] = true;
                    }
                }
            } else // if turnRed == false, then explore Red Graph
            {
                turnRed = true;
                for (int neighbor : redGraph.get(u)) {
                    if (!vis[neighbor][1]) {
                        deq.offer(new int[] { neighbor, cost + 1 });
                        vis[neighbor][1] = true;
                    }
                }
            }
        }
        return dist;
    }

    public int[] shortestAlternatingPaths2(int n, int[][] redEdges, int[][] blueEdges) {
        ArrayList<ArrayList<Integer>> redGraph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> blueGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for (int[] edge : redEdges) {
            int u = edge[0], v = edge[1];
            redGraph.get(u).add(v);
        }
        for (int[] edge : blueEdges) {
            int u = edge[0], v = edge[1];
            blueGraph.get(u).add(v);
        }
        int[] red = bfs2(redGraph, blueGraph, true);
        int[] blue = bfs2(redGraph, blueGraph, false);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (red[i] == Integer.MAX_VALUE && blue[i] == Integer.MAX_VALUE)
                res[i] = -1;
            else
                res[i] = Math.min(red[i], blue[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] redEdge = { { 1, 5 }, { 2, 2 }, { 5, 5 }, { 3, 0 }, { 4, 5 }, { 2, 4 }, { 4, 1 }, { 1, 0 }, { 1, 2 },
                { 5, 2 }, { 2, 3 }, { 0, 1 } };
        int[][] blueEdge = { { 4, 4 }, { 2, 5 }, { 1, 1 }, { 5, 4 }, { 3, 3 } };
        int[] res = new AlternateColorPath().shortestAlternatingPaths(n, redEdge, blueEdge);
        for (int r : res)
            System.out.print(r + " ");
        System.out.println();
    }
}