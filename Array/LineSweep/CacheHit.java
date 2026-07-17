import java.util.*;

public class CacheHit {

    public static List<Integer> getCacheSize(List<List<Integer>> data, List<Integer> queries) {
        int n = data.size();
        long[] startTimes = new long[n];
        long[] endTimes = new long[n];

        for (int i = 0; i < n; i++) {
            long startTime = data.get(i).get(0);
            long ttl = data.get(i).get(1);
            startTimes[i] = startTime;
            endTimes[i] = startTime + ttl;
        }

        // Sort arrays to enable binary search
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        List<Integer> results = new ArrayList<>();

        for (int q : queries) {
            // Count items that have started by time q
            int started = countLessEqual(startTimes, q);

            // Count items that have already expired by time q (strictly less than q)
            int expired = countLess(endTimes, q);

            // Active = (started) - (already expired)
            results.add(started - expired);
        }

        return results;
    }

    // Binary search to find count of elements <= value
    private static int countLessEqual(long[] arr, int value) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= value)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    // Binary search to find count of elements < value
    private static int countLess(long[] arr, int value) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

}
