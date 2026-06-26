public class JumpGameII {
  public int jump(int[] nums) {
    int n = nums.length;
    int maxIndex = 0;
    int steps = 0;
    int currentRange = 0;
    for (int i = 0; i < n - 1; i++) {
      maxIndex = Math.max(maxIndex, i + nums[i]);
      if (currentRange <= i) {
        currentRange = maxIndex;
        steps++;
      }
    }
    return steps;
  }
}
