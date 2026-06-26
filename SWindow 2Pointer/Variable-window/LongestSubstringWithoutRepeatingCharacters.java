import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Deque<Character> deq = new ArrayDeque<>();
        int length = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            int freq = map.getOrDefault(ch, 0) + 1;
            if (freq > 1) {
                while (!deq.isEmpty() && deq.peekFirst() != ch) {
                    char rem = deq.pollFirst();
                    map.remove(rem);
                }
                char rem = deq.pollFirst();
                map.remove(rem);
            }
            deq.addLast(ch);
            map.put(ch, 1);
            length = Math.max(length, deq.size());
        }
        return length;
    }

    public int lengthOfLongestSubstring2(String s) {
        int length = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (map.containsKey(ch))
                left = Math.max(left, map.get(ch) + 1);
                
            map.put(ch, right);
            length = Math.max(length, right - left + 1);

        }
        return length;
    }
}
