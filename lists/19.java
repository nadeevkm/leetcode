/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


/*
  
/        /
d - 1 - null  n = 1

/       /
d - 1 - 2 - null    n = 1 / 2

*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dum = new ListNode();
        dum.next = head;
        ListNode target = dum;
        ListNode curr = head;
        while (n-- > 0){
            curr = curr.next;
        }
        while (curr != null){
            curr = curr.next;
            target = target.next;
        }
        if (target.next == head){
            return head.next;
        } else {
            target.next = target.next.next;
            return head;
        }
    }
}


