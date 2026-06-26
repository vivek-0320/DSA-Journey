import java.util.PriorityQueue;

class MedianFinder {
  PriorityQueue<Integer> small;
  PriorityQueue<Integer> large;

  public MedianFinder() {
    small = new PriorityQueue<>((a, b) -> b - a);
    large = new PriorityQueue<>();
  }

   public void addNum(int num) {
    small.offer(num);
    large.offer(small.poll());
    if (small.size() < large.size()) {
        small.offer(large.poll());
    }
  }

  public double findMedian() {
    if (small.size() == large.size()) {
      return (small.peek() + large.peek()) / (double) (2);
    }
    return small.peek();
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */