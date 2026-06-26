public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int k = 0;
        for (int d = 0; d < m + n - 1; d++) {
            int i, j;
            if (d % 2 == 0) {
                // Even diagonal → go upward
                i = (d < m) ? d : m - 1;
                j = d - i;
                while (i >= 0 && j < n) {
                    res[k++] = mat[i][j];
                    i--;
                    j++;
                }
            } else {
                // Odd diagonal → go downward
                j = (d < n) ? d : n - 1;
                i = d - j;
                while (i < m && j >= 0) {
                    res[k++] = mat[i][j];
                    i++;
                    j--;
                }
            }
        }
        return res;
    }
}
