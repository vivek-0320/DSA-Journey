import java.util.*;

public class QueueReconstruction {
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (a, b) -> {
      if (a[0] != b[0])
        return b[0] - a[0]; // Sort by height descending
      else
        return a[1] - b[1]; // Sort by k-value ascending
    });
    List<int[]> list = new ArrayList<>();
    for (int[] x : people) {
      list.add(x[1], x);
    }
    return list.toArray(new int[list.size()][]);
  }
}
