import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class NGE {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer,Integer> nge = new HashMap<>();
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() < nums2[i]) {
                st.pop();
            }
            nge.put(nums2[i], st.isEmpty() ? -1 : st.peek());
            st.push(nums2[i]);
        }
        int i=0;
        for(int num:nums1)
        {
            ans[i++]=nge.get(num);
        }
        return ans;
    }
}
