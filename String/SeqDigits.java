import java.util.ArrayList;
import java.util.List;

public class SeqDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String master = "123456789";

        // Outer loop determines the length of the number (2 digits up to 9 digits)
        for (int len = 2; len <= 9; len++) {

            // Inner loop slides a window of size 'len' across the master string
            for (int start = 0; start <= master.length() - len; start++) {

                String sub = master.substring(start, start + len);
                int num = Integer.parseInt(sub);

                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}
