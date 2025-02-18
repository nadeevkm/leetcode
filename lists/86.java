class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode partition(ListNode head, int x) {

        ListNode lesDummy = new ListNode();
        ListNode lesCur = lesDummy;

        ListNode geDummy = new ListNode();
        ListNode geCur = geDummy;

        ListNode cur = head; // can be omitted, just use head

        while (cur != null){
            if (cur.val < x){
                lesCur.next = cur;
                lesCur = lesCur.next;
            } else {
                geCur.next = cur;
                geCur = geCur.next;
            }
            cur = cur.next;
        }

        geCur.next = null;
        lesCur.next = geDummy.next;

        return lesDummy.next;
    }
}
