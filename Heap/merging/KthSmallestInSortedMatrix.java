import java.util.*;

public class KthSmallestInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] { matrix[i][0], i, 0 });
        }
        int count = 0;
        int val = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            val = curr[0];
            int row = curr[1], col = curr[2];
            count++;
            if (count == k)
                break;
            if (col + 1 < n) {
                pq.offer(new int[] { matrix[row][col + 1], row, col + 1 });
            }
        }
        return val;
    }
}
