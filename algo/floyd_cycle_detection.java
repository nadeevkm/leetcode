// https://www.youtube.com/watch?v=PvrxZaH_eZ4

// leetcode problem 142

// see jira 287 as well

class CycleDetection{

    public ListNode detect(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null){
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;

            if (slow == fast && slow != null){
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle){
            return null;
        }
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

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


class Check{
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        ListNode n0 = new ListNode(0);

        ListNode n01 = new ListNode(0);
        n01.next = new ListNode(0);

        ListNode n11 = new ListNode(0);
        ListNode n12 = new ListNode(0);
        n11.next = n12;
        n12.next = n11;

        var r1 = new CycleDetection().detect(n1);
        var r2 = new CycleDetection().detect(n0);
        var r3 = new CycleDetection().detect(n01);
        var r4 = new CycleDetection().detect(n11);
        var rr = 0;
    }
}
