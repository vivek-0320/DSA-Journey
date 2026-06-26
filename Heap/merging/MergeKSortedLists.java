
// Definition for singly-linked list.

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
         int k = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for(int i = 0 ; i < k ; i++ ) {
            if(lists[i] != null)
                pq.offer(lists[i]);
        }
        ListNode head = new ListNode(-1,null);
        ListNode t = head;
        while(!pq.isEmpty()) {
            ListNode top = pq.poll();
            t.next = new ListNode(top.val,null);
            if(top.next != null) {
                pq.offer(top.next);
            }
            t = t.next;
        }
        return head.next;
    }
}
