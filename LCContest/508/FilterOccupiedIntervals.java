import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FilterOccupiedIntervals {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        List<List<Integer>> result = new ArrayList<>();
        if (occupiedIntervals == null || occupiedIntervals.length == 0) {
            return result;
        }
        Arrays.sort(occupiedIntervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] prev = occupiedIntervals[0];

        for (int i = 1; i < occupiedIntervals.length; i++) {
            int[] curr = occupiedIntervals[i];
            if (curr[0] <= prev[1] + 1) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                merged.add(prev);
                prev = curr;
            }
        }
        merged.add(prev);

        for (int[] interval : merged) {
            int start = interval[0];
            int end = interval[1];

            // If a portion of the interval exists BEFORE the free time begins
            if (start < freeStart) {
                result.add(Arrays.asList(start, Math.min(end, freeStart - 1)));
            }

            // If a portion of the interval exists AFTER the free time ends
            if (end > freeEnd) {
                result.add(Arrays.asList(Math.max(start, freeEnd + 1), end));
            }
        }

        return result;
    }
}
