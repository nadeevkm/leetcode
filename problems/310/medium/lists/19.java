
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    public ListNode removeNthFromEnd1(ListNode head, int n) { // 2 goes
        if (head.next == null)
            return null;
        var p1 = head;
        int count = 0;
        while (p1 != null){
            p1 = p1.next;
            count++;
        }
        int nodeNum = count - n + 1;
        p1 = head;
        if (nodeNum == 1){
            head = head.next;
        } else {
            for (int i = 1; i != nodeNum - 1; i++) {
                p1 = p1.next;
            }
            var removed = p1.next;
            p1.next = (removed == null) ? null : removed.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = null;
        ListNode p2 = null; // i.e. null -> (head) 1  -> 3 -> 8
        while (true){
            if ( n <= 0) {
                p1 = (p1 == null) ? head : p1.next;
            }
            p2 = (p2 == null) ? head : p2.next;
            n--;
            if (p2.next == null){  // means that p1 is on the place of node prior one to be removed
                if (p1 == head){
                    head = head.next;
                } else {
                    var removed = p1.next;
                    p1.next = (removed == null) ? null : removed.next;
                }
                break;
            }
        }
        return head;
    }
}



/*
  
/        /
d - 1 - null  n = 1

/       /
d - 1 - 2 - null    n = 1 / 2

*/
class SolutionRepetetion {
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


