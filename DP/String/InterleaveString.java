public class InterleaveString {
    int m = 0;
    int n = 0;

    public boolean dfs(int i, int j, String s1, String s2, String s3, Boolean[][] memo) {

        if (i == m && j == n)
            return true;

        if (memo[i][j] != null)
            return memo[i][j];

        // if (i < m && j < n)
        // System.out.printf("i=%d %c, j=%d %c, k=%d
        // %c\n",i,s1.charAt(i),j,s2.charAt(j),i+j,s3.charAt(i+j));

        boolean res = false;
        if (i < m && s1.charAt(i) == s3.charAt(i + j))
            res = dfs(i + 1, j, s1, s2, s3, memo);

        if (!res && j < n && s2.charAt(j) == s3.charAt(i + j))
            res = dfs(i, j + 1, s1, s2, s3, memo);

        memo[i][j] = res;
        return res;

    }

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        Boolean[][] memo = new Boolean[m + 1][n + 1];
        return dfs(0, 0, s1, s2, s3, memo);
    }
}
