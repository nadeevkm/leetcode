import java.util.ArrayDeque;
import java.util.Deque;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


//       lt  si ei rh
//       /  /   / /
//  null 1-2-3-4  -5
// left 0, right 0
// 1-4-3-2-5

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode leftTail = null;
        ListNode endInterval = null;

        ListNode p1 = head;
        while (true){
            p1 = (p1 == null) ? head : p1.next;
            left--;
            right--;
            if (left == 1){
                leftTail = p1;
            }
            if (right == 0){
                endInterval = p1;
                break;
            }
        }

        ListNode rightHead = endInterval.next;
        endInterval.next = null;
        var startInterval = (leftTail == null) ? head : leftTail.next;
        reverseList(startInterval);

        if (leftTail == null) {
            head = endInterval;
        } else {
            leftTail.next = endInterval;
        }
        startInterval.next = rightHead;

        return head;
    }

    public ListNode reverseList(ListNode head){
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        ListNode p1 = head;
        while (p1 != null){
            stack.push(p1);
            head = p1;
            p1 = p1.next;
        }
        ListNode p2 = null;
        while (!stack.isEmpty()){
            if (p2 == null){
                p2 = stack.pop();
            } else {
                p2.next = stack.pop();
                p2 = p2.next;
                p2.next = null;
            }
        }
        return head;
    }
}

class SolutionRepetit {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        var curr = dummy;
        int curP = 0;
        while (++curP != left) {
            curr = curr.next;
        }
        curr.next = reverse(curr.next, left, right);
        return dummy.next;
    }

    private ListNode reverse(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null && left <= right) {
            var next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            left++;
        }
        head.next = curr;
        return prev;
    }
}