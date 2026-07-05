import java.util.Arrays;

public class CountDistinctWaystoFormTargetfromTwoStrings {

    // We use DFS two form the woard target.
    // keep index i on word a, j on word B, and choose the matching character from a or b ,
    // and call the next dfs pass with updated indexes. for base case, if i > 0 & j > 0, i.e, both words have contributed
    // to form target word then we return 1, i.e. 1 way to form the word.


    String a, b, t;
    int[][][] dp = new int[101][101][101];
    int MOD = 1_000_000_007;

    public int dfs(int i, int j, int k) {
        if (k == t.length())
            return (i > 0 && j > 0) ? 1 : 0;
        if (i == a.length() && j == b.length())
            return 0;

        if(dp[i][j][k] != -1)
            return dp[i][j][k];
        long ans = 0;
        for(int x=i;x<a.length();x++)
        {
            if(a.charAt(x) == t.charAt(k))
                ans = (ans + dfs(x+1,j,k+1)) % MOD;
        }
        for(int x=i;x<b.length();x++)
        {
            if(b.charAt(x) == t.charAt(k))
                ans = (ans + dfs(i,x+1,k+1)) % MOD;
        }
        return dp[i][j][k] = (int)ans;


    }

    public int interleaveCharacters(String word1, String word2, String target) {
        a = word1;
        b = word2;
        t = target;
        for (int[][] grid2D : dp) {
            for (int[] row : grid2D) {
                Arrays.fill(row, -1);
            }
        }
        return dfs(0, 0, 0);
    }
}
