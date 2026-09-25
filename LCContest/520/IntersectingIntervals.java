import java.util.Arrays;

public class IntersectingIntervals {
    public int countIntersectingIntervals(int[][] intervals) {
        int count = 0;
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> {
            if(a[1] != b[1])
                return Integer.compare(a[1],b[1]);
            return Integer.compare(a[0],b[0]);
        });
        for (int i = 0; i < n; i++) {
            int[] a = intervals[i];
            for (int j = i + 1; j < n; j++) {
                int[] b = intervals[j];
                if (b[0] <= a[1])
                    count++;
            }
        }
        return count;
    }
}