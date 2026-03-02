public class MinOR {
    public int minimumOR(int[][] grid) {
        int mask = 0;
        for (int k = 29; k >= 0; k--) {
            int testMask = mask | (1 << k);
            boolean allRowsValid = true; // State initialization for the grid

            for (int i = 0; i < grid.length; i++) {
                boolean rowHasValidNumber = false;

                for (int j = 0; j < grid[0].length; j++) {
                    // Check if the current number safely passes the firewall
                    if ((grid[i][j] & testMask) == 0) {
                        rowHasValidNumber = true;
                        break; // Short-circuit the column loop
                    }
                }

                // Fail-Fast: If even one row fails, the entire hypothesis fails
                if (!rowHasValidNumber) {
                    allRowsValid = false;
                    break; // Short-circuit the row loop
                }
            }

            // Commit phase: Only update if EVERY row was valid
            if (allRowsValid) {
                mask = testMask;
            }
        }

        // Bitwise inversion using a 30-bit boundary (2^30 - 1)
        return ((1 << 30) - 1) ^ mask;
    }
}


