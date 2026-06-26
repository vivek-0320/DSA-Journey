public class LongestPalindrome {
  public int longestPalindrome(String s) {
    int[] freq = new int[128];
    for (char ch : s.toCharArray()) {
      freq[ch]++;
    }
    int length = 0;
    boolean hasOdd = false;
    for (int count : freq) {
      if (count == 0)
        continue;
      if (count % 2 == 1) {
        length += count - 1;
        hasOdd = true;
      } else if (count % 2 == 0) {
        length += count;
      }
    }
    return hasOdd ? length + 1 : length;
  }
}
