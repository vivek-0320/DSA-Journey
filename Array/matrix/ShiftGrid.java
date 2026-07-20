import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShiftGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int[] row : grid) {
            for (int el : row)
                nums.add(el);
        }
        Collections.rotate(nums, k);
        List<List<Integer>> result = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < grid.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < grid[0].length; j++) {
                row.add(nums.get(idx));
                idx++;
            }
            result.add(row);
        }
        return result;
    }
}
