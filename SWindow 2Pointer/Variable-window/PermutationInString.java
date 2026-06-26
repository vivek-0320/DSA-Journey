import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PermutationInString {

    public static void printMap(HashMap<Character, Integer> map) {
        System.out.println("Map : ");
        map.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void printSet(Set<Character> set) {
        System.out.println("Set : ");
        set.forEach(ch -> System.out.println(ch));
    }

    public static void printDeq(Deque<Integer> deq) {
        System.out.print("Deq : ");
        deq.forEach(ch -> System.out.print(ch + " "));
        System.out.println();
    }

    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> freq = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        Deque<Integer> deq = new ArrayDeque<>();
        int k = s1.length();
        for (char ch : s1.toCharArray()) { // Building Set And Map
            if (freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) + 1);
            } else {
                freq.put(ch, 1);
                set.add(ch);
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            while (!deq.isEmpty() && (i - deq.peekFirst() + 1) > k) {
                char remove = s2.charAt(deq.pollFirst());
                freq.put(remove, freq.get(remove) + 1);
                if (freq.get(remove) > 0)
                    set.add(remove);
            }
            if (freq.containsKey(ch)) {
                deq.add(i);
                int val = freq.get(ch);
                val--;
                freq.put(ch, val);
                if (val == 0) {
                    set.remove(ch);
                    if (set.size() == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] need = new int[26];
        int[] window = new int[26];
        int k = s1.length();
        for (char ch : s1.toCharArray())
            need[ch - 'a']++;

        for (int i = 0; i < s2.length(); i++) {
            window[s2.charAt(i) - 'a']++;
            if (i >= k) {
                window[s2.charAt(i - k) - 'a']--;
            }
            if (Arrays.equals(need, window)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusionOptimal(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : s1.toCharArray()) {
            need[c - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (need[i] == window[i]) matches++;
        } // considers matched for the characters that were not in S1

        int k = s1.length();
        for (int i = 0; i < s2.length(); i++) {
            int idx = s2.charAt(i) - 'a';
            window[idx]++;
            if (window[idx] == need[idx]) {
                matches++;
            } else if (window[idx] == need[idx] + 1) {
                matches--; // just exceeded from the need 
            }

            if (i >= k) {
                int removeIdx = s2.charAt(i - k) - 'a';
                window[removeIdx]--;
                if (window[removeIdx] == need[removeIdx]) {
                    matches++;
                } else if (window[removeIdx] == need[removeIdx] - 1) {
                    matches--; // just went below need 
                }
            }

            if (matches == 26) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(checkInclusion(s1, s2));
        sc.close();
    }
}
