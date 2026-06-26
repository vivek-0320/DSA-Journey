import java.util.*;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int remove = 0;
        int i = 0, j = 1;
        while (i < intervals.length && j < intervals.length) {
            if (intervals[j][0] < intervals[i][1]) {
                remove++;
                if (intervals[j][1] < intervals[i][1])
                    i = j;
            } else {
                i = j;
            }
            j++;
        }
        return remove;
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int remove = 0;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            if(start <= prevEnd)
                remove++;
            else    
                prevEnd = intervals[i][1];
        }
        return remove;
    }
}
