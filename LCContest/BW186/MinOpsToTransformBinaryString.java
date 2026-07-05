public class MinOpsToTransformBinaryString {


    // We use greedy approach to tarnsofrm the string according to base cases.

    public int minOperations(String s1, String s2) {
        int n = s1.length();

        if (n == 1 && s1.equals("1") && s2.equals("0"))
            return -1;

        int steps = 0;
        char[] s = s1.toCharArray();
        for (int i = 0; i < n; i++) {
            if (s[i] == s2.charAt(i))
                continue;

            if (s[i] == '0' && s2.charAt(i) == '1')
                steps += 1;
            else {
                if (i + 1 < n && s[i + 1] == '1') {
                    steps += 1;
                    s[i] = '0';
                    s[i + 1] = '0';
                } else if (i + 1 < n && s[i + 1] == '0') {
                    steps += 2;
                    s[i] = '0';
                    s[i + 1] = '0';
                } else if (i - 1 >= 0) {
                    steps += 2;
                    s[i] = '0';
                }
            }
        }
        return steps;
    }

}
