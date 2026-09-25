import java.util.Arrays;

public class IntersectingIntervalsII {
    public long countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int[][] events = new int[2 * n][2];

        for (int i = 0; i < n; i++) {
            events[i * 2] = new int[] { intervals[i][0], 0 };
            events[i * 2 + 1] = new int[] { intervals[i][1], 1 };
        }

        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int activeIntervals = 0;
        long totalIntersections = 0;

        for (int[] event : events) {
            if (event[1] == 0) {
                totalIntersections += activeIntervals;
                activeIntervals++;
            } else {
                activeIntervals--;
            }
        }

        return totalIntersections;
    }
}
