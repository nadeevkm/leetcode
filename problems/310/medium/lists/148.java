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


//
//  5

//    c
//    l
//    5 - 8
// d  l  r  rn
//    5. 8

//  8 - 5
//  1
//  c
//  l
//  4 - 2 - 1 -3
//
class Solution {
    public ListNode sortList(ListNode head) {  // iterativ merge sort with O(1) memory
        ListNode dummy = new ListNode();
        dummy.next = head;

        int interval = 1;
        ListNode prev = dummy;
        ListNode current = head;
        while (current != null){
            ListNode left = current;
            ListNode leftNext = split(left, interval);  // left, leftNext - can be removed, here for explicity
            ListNode right = leftNext;
            if (left == dummy.next && right == null){
                return dummy.next;
            }
            ListNode rightNext = split(right, interval);

            ListNode mergeTail = merge(prev, left, right);
            mergeTail.next = rightNext;
            prev = mergeTail;
            current = mergeTail.next;

            if (current == null){
                current = dummy.next;
                prev = dummy;
                interval = 2*interval;
            }
        }
        return dummy.next;
    }

    public ListNode split(ListNode node, int interval){
        ListNode followingNode = null;
        while (interval > 1 && node != null){
            node = node.next;
            interval--;
        }
        if (node != null) {
            followingNode = node.next;
            node.next = null;
        }
        return followingNode;
    }

    public ListNode merge(ListNode prev, ListNode node1, ListNode node2){
//        ListNode prev = new ListNode(); // i.e. dummy
        ListNode current = prev;
        var p1 = node1;
        var p2 = node2;

        while (p1 != null || p2 != null){
            if (p1 == null){
                current.next = p2;
                p2 = p2.next;
            } else if (p2 == null){
                current.next = p1;
                p1 = p1.next;
            } else if (p1.val < p2.val){
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }
        // prev.next - head, current - tail
        return current;
    }
}

class SolutionRepetit {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        var pair = split(head);
        var left = sortList(pair[0]);
        var right = sortList(pair[1]);
        return merge(left, right);
    }

    private ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (first != null || second != null) {
            if (second == null || (first != null && first.val < second.val)) {
                curr.next = first;
                first = first.next;
            } else {
                curr.next = second;
                second = second.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    private ListNode[] split(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        var slowNext = slow.next;
        slow.next = null;
        // fast.next = null; !!! erroneous string, might delete relations which will be needed further, cause fast is not always comes to an end of the list! (might end up on second to last one)
        return new ListNode[]{node, slowNext};
    }
}
