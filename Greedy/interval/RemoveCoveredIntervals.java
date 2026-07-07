import java.util.Arrays;

class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        int[] prev = intervals[0];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[1] <= prev[1])
                count++;
            else
                prev = curr;
        }
        return intervals.length - count;
    }

}