import java.util.Arrays;

// The Two Runners: Track two timelines simultaneously:
//  j (0 replacements allowed, strict matches only) and i (1 replacement allowed, flexible).
// The Cheat Code: For each character in t, runner i can either match naturally, 
// or use its one replacement to jump ahead of j's old position using i = max(i, j + 1).
// The Golden Rule: You must calculate i's next move before you let j move. 
// This guarantees the current character in t is consumed exactly once per turn.
// The Finish Line: Runner j only moves on an exact match; 
// if either runner successfully reaches the end of string s, return true!

public class SubsequenceAfterOneReplacement {
    public boolean canMakeSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (m > n)
            return false;

        int[] left = new int[m];
        Arrays.fill(left, Integer.MAX_VALUE);

        int tIdx = 0;
        for (int i = 0; i < m; i++) {
            while (tIdx < n && t.charAt(tIdx) != s.charAt(i)) {
                tIdx++;
            }
            if (tIdx < n) {
                left[i] = tIdx;
                tIdx++;
            } else {
                break;
            }
        }

        int[] right = new int[m];
        Arrays.fill(right, -1);

        tIdx = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (tIdx >= 0 && t.charAt(tIdx) != s.charAt(i)) {
                tIdx--;
            }
            if (tIdx >= 0) {
                right[i] = tIdx;
                tIdx--;
            } else {
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            int L = (i == 0) ? -1 : left[i - 1];
            int R = (i == m - 1) ? n : right[i + 1];
            if (L != Integer.MAX_VALUE && R != -1 && (R - L) >= 2) {
                return true;
            }
        }

        return false;
    }
}
