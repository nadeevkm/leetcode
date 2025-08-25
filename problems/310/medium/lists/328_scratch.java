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

class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummyOdd = new ListNode();
        ListNode dummyEven = new ListNode();
        var odd = dummyOdd;
        var even = dummyEven;
        var isOdd = true;
        while (head != null){
            if (isOdd){
                odd.next = head;
                odd = odd.next;
                head = head.next;
                odd.next = null; // !! - attention, can't do that before head = head.next or will tear the chain
            } else {
                even.next = head;
                even = even.next;
                head = head.next;
                even.next = null;
            }
            isOdd = !isOdd;
        }
        odd.next = dummyEven.next;
        return dummyOdd.next;
    }
}

class SolutionALittleBitConciser { // base processing on even list, next node after it before the end of processing is always odd
    public ListNode oddEvenList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode odd = head;
        ListNode evenFirst = head.next;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenFirst;
        return head;
    }
}