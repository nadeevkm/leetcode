
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    // 342  + 465 = 807

    //          /
    // l1 = [2,4,3],
    //         /
    // l2 = [5,6,4]
    // l =  7 - 0 - 8,  mem =  1

    // 99 + 1
    //        /
    // [9, 9]
    //     /
    // [1]
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var p1 = l1;
        var p2 = l2;
        int mem = 0;
        ListNode node = null;
        ListNode lastNode = null;
        while (p1 != null || p2 != null || mem > 0){
            int num1 = (p1 == null) ? 0 : p1.val;
            int num2 = (p2 == null) ? 0 : p2.val;
            int res = num1 + num2 + mem;
            if (res > 9){
                mem = 1;
                res = res % 10;
            } else {
                mem = 0;
            }
            var currNode = new ListNode(res);
            if (node == null){
                node = currNode;
                lastNode = currNode;
            } else {
                lastNode.next = currNode;
                lastNode = currNode;
            }
            p1 = (p1 == null) ? null : p1.next;
            p2 = (p2 == null) ? null : p2.next;
        }
        return node;
    }
}
