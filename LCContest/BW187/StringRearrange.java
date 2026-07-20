public
package BW187;

class StringRearrange {

    public String rearrangeString(String s, char x, char y) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray())
            freq[ch - 'a']++;
        StringBuilder sb = new StringBuilder(y);
        while (freq[y - 'a'] != 0) {
            freq[y - 'a']--;
            sb.append(y);
        }
        for (int i = 0; i < 26; i++) {
            while (freq[i] != 0) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();

    }
}