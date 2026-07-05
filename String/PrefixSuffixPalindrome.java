import java.util.Scanner;

public class PrefixSuffixPalindrome {

    public static String solve(String word) {
        int n = word.length();
        if (n == 1)
            return word;
        StringBuilder first = new StringBuilder();
        StringBuilder last = new StringBuilder();
        int l = 0;
        int r = n - 1;
        while (l < r && word.charAt(l) == word.charAt(r)) {
            first.append(word.charAt(l));
            last.append(word.charAt(r));
            l++;
            r--;
        }
        int maxLen = 0;
        int maxStart = 0;
        String middle = "";
        if (l <= r) {
            middle = word.substring(l, r + 1);
            int len = middle.length();
            char[] t = new char[2 * len + 3];
            int[] p = new int[t.length];
            t[0] = '^';
            t[2 * len + 2] = '$';
            for (int i = 0; i < middle.length(); i++) {
                t[2 * i + 1] = '#';
                t[2 * i + 2] = middle.charAt(i);
            }
            t[2 * len + 1] = '#';
            int c = 0;
            int right = 0;

            for (int i = 1; i < t.length - 1; i++) {
                if (i < right)
                    p[i] = Math.min(p[2 * c - i], right - i);
                else
                    p[i] = 0;

                while (t[i + 1 + p[i]] == t[i - 1 - p[i]])
                    p[i]++;

                if (i + p[i] > right) {
                    c = i;
                    right = i + p[i];
                }
            }
            for (int i = 1; i < t.length - 1; i++) {
                if (i - p[i] == 1 || i + p[i] == t.length - 2) // prefix or suffix
                {
                    if (p[i] > maxLen) {
                        maxLen = p[i];
                        maxStart = (i - 1 - p[i]) / 2;
                    }
                }
            }

        }
        String longestMiddle = middle.substring(maxStart, maxStart + maxLen);
        return first.toString() + longestMiddle + last.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- != 0) {
            String word = sc.nextLine();
            System.out.println(solve(word));
        }
        sc.close();
    }
}
