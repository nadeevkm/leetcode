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
    public void deleteNode(ListNode node) {
        while (node.next.next != null){
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
}

class SolutionOptimal { // no need to copy whole list after !!! just copy prev node
     public void deleteNode(ListNode node) {
         node.val = node.next.val;
         node.next = node.next.next;
    }
}
