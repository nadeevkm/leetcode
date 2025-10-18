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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode res = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int val = sum % 10;
            ListNode nextDigit = new ListNode(val);
            res.next = nextDigit;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return reverseList(dummy.next);
    }

    private ListNode reverseList(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            var next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}


class Check{
    public static void main(String[] args) {
        var l1 = new ListNode(1);
        var l2 = new ListNode(2);
        l1.next = l2;
        var r = new Solution().addTwoNumbers(l1, null);
    }
}


class SolutionRepetit {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode sum = add(l1, l2);
        return reverse(sum);
    }

    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    private ListNode add(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0){
            var next = carry;
            if (l1 != null){
                next += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                next += l2.val;
                l2 = l2.next;
            }
            var value = next % 10;
            carry = next / 10;
            curr.next = new ListNode(value);
            curr = curr.next;
        }
        return dummy.next;
    }
}