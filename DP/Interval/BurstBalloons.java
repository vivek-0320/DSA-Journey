import java.util.*;

public class BurstBalloons {
  public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, (a, b) -> {
      if (a[0] != b[0])
        return Integer.compare(a[0], b[0]);
      else
        return Integer.compare(a[1], b[1]);
    });
    List<int[]> range = new ArrayList<>();
    for (int[] point : points) {
      int lastIdx = range.size() - 1;
      if (range.isEmpty() || (point[0] > range.get(lastIdx)[1])) {
        range.add(point);
      } else {
        range.get(lastIdx)[0] = Math.max(range.get(lastIdx)[0], point[0]);
        range.get(lastIdx)[1] = Math.min(range.get(lastIdx)[1], point[1]);
      }
    }
    return range.size();
  }
}
