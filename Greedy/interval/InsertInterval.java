import java.util.*;

public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> merged = new ArrayList<>();
    boolean hasMerged = false;
    for (int[] interval : intervals) {
      int lastIdx = merged.size() - 1;

      if (!hasMerged && newInterval[0] < interval[0]) {
        if (merged.isEmpty() || merged.get(lastIdx)[1] < interval[0]) {
          merged.add(interval);
        } else {
          merged.get(lastIdx)[1] = Math.max(merged.get(lastIdx)[1], newInterval[1]);
        }
        hasMerged = true;
      }

      if (merged.isEmpty() || merged.get(lastIdx)[1] < interval[0]) {
        merged.add(interval);
      } else {
        merged.get(lastIdx)[1] = Math.max(merged.get(lastIdx)[1], interval[1]);
      }
    }
    return merged.toArray(new int[merged.size()][]);
  }
}
