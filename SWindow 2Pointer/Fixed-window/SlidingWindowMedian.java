import java.util.TreeMap;
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];
        TreeMap<Integer, Integer> left = new TreeMap<>(); // max half
        TreeMap<Integer, Integer> right = new TreeMap<>(); // min half
        int leftSize = 0, rightSize = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
            }

            while (leftSize > rightSize + 1) {
                int key = left.lastKey();
                moveKey(key, left, right);
                leftSize--;
                rightSize++;
            }

            while (rightSize > leftSize) {
                int key = right.firstKey();
                moveKey(key, left, right);
                rightSize--;
                leftSize++;
            }

            if (i >= k - 1) {
                if (k % 2 == 1)
                    res[i - k + 1] = left.lastKey();
                else
                    res[i - k + 1] = ((long) left.lastKey() + (long) right.firstKey()) / 2.0;

                // remove outgoing element
                int toRemove = nums[i - k + 1];
                if (left.containsKey(toRemove)) {
                    removeKey(toRemove, left);
                    leftSize--;
                } else {
                   removeKey(toRemove, right);
                    rightSize--;
                }

                // rebalance after removal
                while (leftSize > rightSize + 1) {
                    int key = left.lastKey();
                    moveKey(key, left, right);
                    leftSize--;
                    rightSize++;
                }

                while (rightSize > leftSize) {
                    int key = right.firstKey();
                    moveKey(key, right, left);
                    rightSize--;
                    leftSize++;
                }
            }
        }
        return res;
    }

    public void moveKey(int key, TreeMap<Integer, Integer> src, TreeMap<Integer, Integer> dest) {
        removeKey(key, src);
        dest.put(key, dest.getOrDefault(key, 0) + 1);
    }

    public void removeKey(int key, TreeMap<Integer, Integer> map) {
        if (map.get(key) == 1)
            map.remove(key);
        else
            map.put(key, map.get(key) - 1);
    }
}
