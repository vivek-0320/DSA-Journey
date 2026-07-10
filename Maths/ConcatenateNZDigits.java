
public class ConcatenateNZDigits {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1_000_000_007;

        long[] prefSum = new long[n + 1];
        int[] nzCount = new int[n + 1];
        long[] prefVal = new long[n + 1];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            prefSum[i + 1] = prefSum[i] + digit;

            if (digit != 0) {
                nzCount[i + 1] = nzCount[i] + 1;
                prefVal[i + 1] = (prefVal[i] * 10 + digit) % MOD;
            } else {
                nzCount[i + 1] = nzCount[i];
                prefVal[i + 1] = prefVal[i];
            }
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Total sum of digits in the range
            long sum = prefSum[r + 1] - prefSum[l];

            int k = nzCount[r + 1] - nzCount[l];

            long valR = prefVal[r + 1];
            long valL = prefVal[l];
            long shiftedLeft = (valL * pow10[k]) % MOD;

            long x = (valR - shiftedLeft) % MOD;

            if (x < 0) {
                x += MOD;
            }

            res[i] = (int) ((x * sum) % MOD);
        }
        return res;
    }

}
