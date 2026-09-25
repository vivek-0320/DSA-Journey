public class LexicographicallyLargestString {
    public String[] largestString(int[] nums) {
        int[] score = new int[26];
        score[0] = 1;
        for (int i = 1; i < 26; i++)
            score[i] = 2 * score[i - 1];

        int n = nums.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int j = 25;
            StringBuilder sb = new StringBuilder();
            while(num != 0 && j > -1)
            {
                if(num >= score[j])
                {
                    sb.append((char)(j+'a'));
                    num -= score[j];
                }
                else
                {
                    j--;
                }
            }
            res[i] = sb.toString();
        }
        return res;
    }
}
