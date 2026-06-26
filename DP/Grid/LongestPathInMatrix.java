public class LongestPathInMatrix {

    private static final int[][] DIRECTIONS = {{0,-1},{0,1},{1,0},{-1,0}};
    int r,c;

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;

        r = matrix.length;
        c = matrix[0].length;

        int[][] memo = new int[r][c];
        int longestPath = 0 ;

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                int path = dfs(matrix,i,j,memo);
                longestPath = Math.max(longestPath, path);
            }
        }
        return longestPath;

    }

    int dfs(int[][] matrix, int row, int col, int[][] memo)
    {
        if(memo[row][col] != 0)
            return memo[row][col];

        int maxPathFromHere = 1;

        for(int[] dir : DIRECTIONS)
        {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow>=0 && newRow < r && newCol >= 0 && newCol < c && matrix[newRow][newCol] > matrix[row][col])
            {
                int pathLength = 1 + dfs(matrix,newRow,newCol,memo);
                maxPathFromHere = Math.max(maxPathFromHere,pathLength);
            }
        }
        memo[row][col] = maxPathFromHere;
        return maxPathFromHere;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
        LongestPathInMatrix ob = new LongestPathInMatrix();
        System.out.println(ob.longestIncreasingPath(matrix));
    }
}