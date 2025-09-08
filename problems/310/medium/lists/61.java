class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


//         /  / /
//    0-1-2  3-4

// k = 2
// 3-4-0-1-2
class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) return null;
        if (head.next == null) return head;

        ListNode p = head;
        int count = 0;
        while (p != null) {
            p = p.next;
            count++;
        }
        int rotation = k % count;
        if (rotation == 0){
            return head;
        }

        ListNode newTail = null;
        ListNode oldTail = null;
        while (true) {
            oldTail = (oldTail == null) ? head : oldTail.next;
            if (rotation == 0) {
                newTail = (newTail == null) ? head : newTail.next;
            } else {
                rotation--;
            }
            if (oldTail.next == null)
                break;
        }
        ListNode newHead = (newTail == null) ? head : newTail.next;
        newTail.next = null;
        oldTail.next = head;

        return newHead;
    }
}

class SolutionRepetetion {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode curr = head;
        ListNode tail = head;
        int size = 0;
        while (curr != null){
            size++;
            tail = curr;
            curr = curr.next;
        }
        if (size == 0 || size == 1 || k == 0 || k % size == 0){
            return head;
        }
        int newTailNum = size - k % size;
        curr = head;
        int i = 1;
        while (i != newTailNum){
            curr = curr.next;
            i++;
        } // curr here is new tail
        ListNode newHead = curr.next;
        curr.next = null;
        tail.next = head;
        return newHead;
    }
}