import java.util.HashMap;

public class Q2 {
    public int getLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            int[] countFreq = new int[n + 1]; 
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
                int currentElement = nums[j];
                int oldFreq = freq.getOrDefault(currentElement, 0);
                int newFreq = oldFreq + 1;
                
                freq.put(currentElement, newFreq);

                if (oldFreq > 0) {
                    countFreq[oldFreq]--; 
                }
                countFreq[newFreq]++; 

                if (newFreq > maxFreq) {
                    maxFreq = newFreq;
                }

                int uniqueCount = freq.size();
                int currentLength = j - i + 1;

                if (uniqueCount == 1) {
                    maxLen = Math.max(maxLen, currentLength);
                } 
                else if (maxFreq % 2 == 0) {
                    int elementsAtMax = countFreq[maxFreq];
                    int elementsAtHalf = countFreq[maxFreq / 2];
                    if (elementsAtMax + elementsAtHalf == uniqueCount) {
                        maxLen = Math.max(maxLen, currentLength);
                    }
                }
            }
        }
        return maxLen;
    }

}
