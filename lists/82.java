
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    //     du
    //                     lu
    //                        c   n
    // head = [1,2,3,3,4,4,5]
    //      d-1-2-         5
    //  ---------------------
    //     du
    //         lu
    //            cn
    // head = [1]
    //      d-1
    // ---------------------
    //     du
    //     lu
    //               c  n
    // head = [1, 1]
    //      d-
    // ----------------------
    //      du
    //                     lu
    //                        c   n
    // head = [1, 1, 1, 2, 3]
    //      d-2-3
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode lastUnique = dummy;
        ListNode current = head;
        ListNode nextNode = head.next;

        while (lastUnique != null){  // !!! - mistook here
            if (nextNode != null && current.val == nextNode.val){ // !!! - mistook here
                while (nextNode != null && current.val == nextNode.val){  // !!! - mistook here
                    nextNode = nextNode.next;
                }
            } else {
                lastUnique.next = current;
                lastUnique = (lastUnique.next == null) ? null : lastUnique.next;
            }
            current = nextNode;
            nextNode = (nextNode == null) ? null : nextNode.next;
        }

        return dummy.next;
    }

    //      du
    //      pr
    //                       c
    // head = [1,2,3,3,4,4,5]
    //      d-1-2-5-n
    //  ---------------------
    //      du
    //      pr     c
    // head = [1,1]
    //      d-
    //  ---------------------
    public ListNode deleteDuplicates1(ListNode head) {  // two pointers only

        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;

        while (prev != null){
            if (curr == null){
                prev.next = null;
                prev = null; // prev.next
            } else if (curr.next == null || curr.next.val != curr.val) {  // !!! - compare by .val
                prev.next = curr;
                prev = prev.next;

                curr = curr.next;
            } else { // curr.next == curr
                while (curr.next != null && curr.next.val == curr.val){
                    curr = curr.next;
                }
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}

class Check{
    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,3,4,4,5};
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        for (var v : data){
            ListNode node = new ListNode(v);
            prev.next = node;
            prev = prev.next;
        }

        new Solution().deleteDuplicates1(dummy.next);
    }
}
