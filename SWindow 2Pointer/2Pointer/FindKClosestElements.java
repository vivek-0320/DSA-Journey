import java.util.*;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = k;
        while (right < arr.length) {
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)
                    || (Math.abs(arr[left] - x) == Math.abs(arr[right] - x) && arr[left] == arr[right])) {
                left++;
            } else {
                break;
            }
            right++;
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i < right; i++) {
            result.add(i, arr[i]);
        }
        return result;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        while (right - left + 1 > k) {
            if (Math.abs(arr[left] - x) > Math.abs(arr[right] - x)) {
                left++;
            } else {
                right--;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public List<Integer> findClosestElementsOptimal(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
