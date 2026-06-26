import java.util.*;

public class HandsOfStraight {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int card : hand)
            map.put(card, map.getOrDefault(card, 0) + 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // { number , frequency }
        for (int card : map.keySet()) {
            pq.offer(new int[] { card, map.get(card) });
        }

        Queue<int[]> next = new LinkedList<>();
        while (!pq.isEmpty()) {
            int[] group = new int[groupSize];
            for (int i = 0; i < groupSize; i++) {
                if (pq.isEmpty())
                    return false;
                int[] top = pq.poll();
                System.out.println(Arrays.toString(top));
                group[i] = top[0];
                if (i > 0 && group[i] != group[i - 1] + 1)
                    return false;
                top[1]--;
                if (top[1] > 0)
                    next.add(top);
            }
            while (!next.isEmpty()) {
                pq.add(next.poll());
            }
        }
        return true;
    }

    public boolean isNStraightHand2(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int card : hand)
            map.put(card, map.getOrDefault(card, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int card : map.keySet()) {
            pq.offer(card);
        }

        while (!pq.isEmpty()) {
            int first = pq.peek();
            for (int card = first; card < first + groupSize; card++) {
                if (!map.containsKey(card))
                    return false;
                map.put(card, map.get(card) - 1);
                if (map.get(card) == 0) {
                    map.remove(card);
                    pq.remove(card);
                }
            }
        }
        return true;
    }
}
