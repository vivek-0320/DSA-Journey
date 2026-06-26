public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] map = new int[26];
            int maxFreq = 0;
            for (int j = i; j < s.length(); j++) {
                map[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, map[s.charAt(j) - 'A']);
                if ((j - i + 1) - maxFreq > k) {
                    break;
                }
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }

    public int characterReplacementOptimal(String s, int k) {
        int maxLen = 0, left = 0, right = 0, maxFreq = 0;
        int[] map = new int[26];
        while (right < s.length()) {
            map[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, map[s.charAt(right) - 'A']);
            while ((right - left + 1) - maxFreq > k) {
                map[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
