import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeocodeWays {

    public int dfs(int idx, String s, int[] dp, List<String> path) {
        if (idx == s.length()) {
            System.out.println(path);
            return 1;
        }

        if (s.charAt(idx) == '0')
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        path.add(s.substring(idx, idx + 1));
        int ways = dfs(idx + 1, s, dp, path);
        path.removeLast();

        if (idx + 1 < s.length()
                && (s.charAt(idx) == '1' || (s.charAt(idx) == '2' && "0123456".indexOf(s.charAt(idx + 1)) > -1))) {
            path.add(s.substring(idx, idx + 2));
            ways += dfs(idx + 2, s, dp, path);
            path.removeLast();
        }

        dp[idx] = ways;

        return dp[idx];
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        List<String> path = new ArrayList<>();
        return dfs(0, s, dp, path);
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // 1 way to decode an empty string
        dp[1] = 1; // 1 way to decode a single valid character (we already ruled out '0')

        // Start filling the DP table from length 2
        for (int i = 2; i <= n; i++) {

            // The characters are 0-indexed, so length `i` corresponds to index `i-1`
            char curr = s.charAt(i - 1);
            char prev = s.charAt(i - 2);

            // Choice 1: Take 1 digit
            if (curr != '0') {
                dp[i] += dp[i - 1];
            }

            // Choice 2: Take 2 digits
            if (prev == '1' || (prev == '2' && curr <= '6')) {
                // We can append this 2-digit block to all valid ways from dp[i-2]
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
