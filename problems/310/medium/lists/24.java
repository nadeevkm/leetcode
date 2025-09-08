class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class SolutionIter {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        var prev = dummy;
        while(head.next != null){
            var next = head.next;
            var curr = head;
            prev.next = next;
            head = next.next;
            next.next = curr;
            prev = curr;
            curr.next = head;
        }
        return dummy.next;
    }
}


class SolutionRec {
    public ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }
        return swapPairs(head, head.next);
    }

    public ListNode swapPairs(ListNode p1, ListNode p2){
        if (p2 == null){
            return p1;
        }
        var nextP1 = p2.next;
        var nextP2 = p2.next != null ? p2.next.next : null;
        var next = swapPairs(nextP1,nextP2);
        p1.next = next;
        p2.next = p1;
        return p2;
    }
}

class SolutionRepetit {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode second = head.next;
        head.next = swapPairs(head.next.next);
        second.next = head;

        return second;
    }
}