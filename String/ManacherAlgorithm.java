import java.util.Arrays;

public class ManacherAlgorithm {
    public int countSubstrings(String s) {
        int n = s.length();
        char[] t = new char[2 * n + 3];
        t[0] = '^';
        t[2 * n + 2] = '$';
        for (int i = 0; i < n; i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[2 * n + 1] = '#';

        int[] p = new int[t.length];
        int c = 0;
        int r = 0;
        for (int i = 1; i < t.length - 1; i++) {
            if (2 * c - i >= 0 && i <= r)
                p[i] = Math.min(p[2 * c - i], r - i);
            else
                p[i] = 0;

            while (t[i + 1 + p[i]] == t[i - 1 - p[i]])
                p[i] += 1;

            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }
        // System.out.println(Arrays.toString(p));
        // Because of the # padding, if a center has a radius of 3 (meaning a palindrome of length 3, like "aba"),
        // it contains 2 valid palindromes ("b" and "aba"). If a center has a radius of 4 (like "abba"),
        // it also contains 2 valid palindromes ("bb" and "abba").
        int count = 0;
        for (int num : p) {
            count += (num + 1) / 2;
        }
        return count;
    }

    public static void main(String[] args) {
        ManacherAlgorithm ob = new ManacherAlgorithm();
        ob.countSubstrings("aaa");
    }
}
