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
    public void reorderList(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        // find mid of the list (than reverse second part and merge than one by one)
        while (p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }

        ListNode curr = p1.next;
        // torn this two lists apart
        p1.next = null;
        // reverse second list
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        p1 = head;
        p2 = prev; // reverse list head;
        // merge two lists
        while (p2 != null) {
            ListNode p1next = p1.next;
            ListNode p2next = p2.next;

            p1.next = p2;
            p1.next.next = p1next;

            p1 = p1next;
            p2 = p2next;
        }
        //return head;
    }
}

class SolutionRepetit {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l2 = reverse(slow.next);
        slow.next = null;
        ListNode l1 = head;
        merge(l1, l2);
        //return head;
    }

    private void merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while(l1 != null || l2 != null){
            if (l1 != null){
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            }
            if (l2 != null){
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }
        //return dummy.next;
    }

    private ListNode reverse(ListNode curr){
        ListNode prev = null;
        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}