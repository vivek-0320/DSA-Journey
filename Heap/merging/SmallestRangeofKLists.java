import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeofKLists {
  static class Node {
    int val; // The number itself
    int list; // Which list this number came from
    int idx; // Index of the number inside that list

    Node(int val, int list, int idx) {
      this.val = val;
      this.list = list;
      this.idx = idx;
    }
  }

  public int[] smallestRange(List<List<Integer>> nums) {
    PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
    int max = Integer.MIN_VALUE;
    int[] range = new int[2];

    // Step 1: Initialize heap with first element from each list
    for (int i = 0; i < nums.size(); i++) {
      int value = nums.get(i).get(0);
      minHeap.offer(new Node(value, i, 0));
      max = Math.max(max, value);
    }

    int bestRange = Integer.MAX_VALUE;
    range[0] = -1;
    range[1] = -1;

    // Step 2: Process while all lists are represented in the heap
    while (minHeap.size() == nums.size()) {
      Node curr = minHeap.poll();
      int min = curr.val;

      // Update answer if we found a smaller range
      if (max - min < bestRange) {
        bestRange = max - min;
        range[0] = min;
        range[1] = max;
      }

      // Move to next element in the same list
      if (curr.idx + 1 < nums.get(curr.list).size()) {
        int nextVal = nums.get(curr.list).get(curr.idx + 1);
        minHeap.offer(new Node(nextVal, curr.list, curr.idx + 1));
        max = Math.max(max, nextVal);
      } else {
        // One of the lists is exhausted → we can’t form a full window anymore
        break;
      }
    }
    return range;
  }
}
