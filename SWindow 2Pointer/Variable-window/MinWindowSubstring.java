import java.util.*;

public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.isEmpty() || t.length() > s.length()) {
            return "";
        }
        int[] need = new int[128];
        int[] window = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        int required = 0;
        for (int count : need) {
            if (count > 0) {
                required++;
            }
        }
        int left = 0;
        int have = 0; // The number of unique characters in the window that meet the requirement.
        int start = -1; // Start index of the result string.
        int minLen = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            window[ch]++;
            if (need[ch] > 0 && window[ch] == need[ch]) {
                have++;
            }
            while (have == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                char rem = s.charAt(left);
                // IMPORTANT: Check if its removal will break a match.
                // This must be done BEFORE decrementing its count in the window.
                if (need[rem] > 0 && window[rem] == need[rem]) {
                    have--;
                }
                window[rem]--;
                left++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    public static String minWindow2(String s, String t) { // Brute Force
        if (t.length() > s.length())
            return "";
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char ch : t.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        int matches = freq.size();
        String ans = null;
        for (int x = 0; x < s.length(); x++) {
            int cMatch = matches;
            HashMap<Character, Integer> copy = new HashMap<>(freq);
            StringBuilder window = new StringBuilder();
            for (int y = x; y < s.length(); y++) {
                char ch = s.charAt(y);
                window.append(ch);
                if (copy.containsKey(ch) && copy.get(ch) > 0) {
                    copy.put(ch, copy.get(ch) - 1);
                    if (copy.get(ch) == 0)
                        cMatch--;
                }
                if (cMatch == 0) {
                    if (ans == null || window.length() < ans.length()) {
                        ans = window.toString();
                        break;
                    }
                }
            }
        }
        return ans == null ? "" : ans;
    }

    public static String minWindow4(String s, String t) { // Brute Force without StringBuilder
        if (t.length() > s.length())
            return "";

        // Frequency map of characters in t
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char ch : t.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int matches = freq.size();
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int x = 0; x < s.length(); x++) {
            int cMatch = matches;
            HashMap<Character, Integer> copy = new HashMap<>(freq);

            for (int y = x; y < s.length(); y++) {
                char ch = s.charAt(y);

                if (copy.containsKey(ch) && copy.get(ch) > 0) {
                    copy.put(ch, copy.get(ch) - 1);
                    if (copy.get(ch) == 0)
                        cMatch--;
                }

                if (cMatch == 0) {
                    // Update answer using indices instead of building string
                    if (y - x + 1 < minLen) {
                        minLen = y - x + 1;
                        startIndex = x;
                    }
                    break; // Found a valid window starting at x, no need to extend further
                }
            }
        }

        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }

    public static String minWindow3(String s, String t) { // Brute Force using array
        if (t.length() > s.length())
            return "";
        int[] freq = new int[128]; // required counts
        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int matches = 0;
        for (int i = 0; i < 128; i++) {
            if (freq[i] > 0)
                matches++;
        }

        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int x = 0; x < s.length(); x++) {
            int cMatch = matches;
            int[] copy = freq.clone(); // copy of required counts

            for (int y = x; y < s.length(); y++) {
                char ch = s.charAt(y);

                if (copy[ch] > 0) {
                    copy[ch]--;
                    if (copy[ch] == 0)
                        cMatch--;
                }

                if (cMatch == 0) {
                    if (y - x + 1 < minLen) {
                        minLen = y - x + 1;
                        startIndex = x;
                    }
                    break; // found valid window starting at x
                }
            }
        }

        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }

    public static String minWindow5(String s, String t) { // Brute force using 1 array
        if (t.length() > s.length())
            return "";

        int[] freq = new int[128]; // required counts
        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int matches = 0;
        for (int i = 0; i < 128; i++)
            if (freq[i] > 0)
                matches++;

        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int x = 0; x < s.length(); x++) {
            int cMatch = matches;
            int[] used = new int[128]; // track how many times each char is used in current window

            for (int y = x; y < s.length(); y++) {
                char ch = s.charAt(y);
                if (freq[ch] > 0) {
                    used[ch]++;
                    if (used[ch] == freq[ch])
                        cMatch--;
                }

                if (cMatch == 0) {
                    if (y - x + 1 < minLen) {
                        minLen = y - x + 1;
                        startIndex = x;
                    }
                    break; // found valid window starting at x
                }
            }
        }

        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.nextLine();
        String t = sc.next();
        System.out.println(minWindow2(s, t));
        sc.close();
    }
}
