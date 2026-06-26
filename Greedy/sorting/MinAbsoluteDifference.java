import java.util.*;

public class MinAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(arr[i] - arr[i - 1], minDiff);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                List<Integer> list = Arrays.asList(arr[i - 1], arr[i]);
                result.add(list);
            }
        }
        return result;
    }

    public List<List<Integer>> minimumAbsDifference2(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            int newDiff = arr[i] - arr[i - 1];
            if (newDiff < minDiff) {
                minDiff = newDiff;
                result.clear();
            }
            if (newDiff == minDiff) {
                List<Integer> list = Arrays.asList(arr[i - 1], arr[i]);
                result.add(list);
            }
        }
        return result;
    }
}
