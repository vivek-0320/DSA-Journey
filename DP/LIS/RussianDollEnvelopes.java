import java.util.ArrayList;
import java.util.Arrays;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });
        int n = envelopes.length;
        ArrayList<int[]> tails = new ArrayList<>();
        tails.add(envelopes[0]);
        for (int i = 1; i < n; i++) {
            int[] last = tails.getLast();
            if (last[0] < envelopes[i][0] && last[1] < envelopes[i][1]) {
                tails.add(envelopes[i]);
                // System.out.printf("Add [%d,%d] directly\n",envelopes[i][0],envelopes[i][1]);
            } else {
                int low = 0;
                int high = tails.size() - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (tails.get(mid)[0] < envelopes[i][0] && tails.get(mid)[1] < envelopes[i][1]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                // System.out.printf("Swapped [%d,%d] with [%d,%d]
                // \n",tails.get(low)[0],tails.get(low)[1],envelopes[i][0],envelopes[i][1]);
                tails.set(low, envelopes[i]);

            }
        }
        return tails.size();
    }
}
