# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        odd = dummyOdd = ListNode()
        even = dummyEven = ListNode()
        while head != None:
            odd.next = head
            odd = odd.next
            head = head.next
            if head != None:
                even.next = head
                even = even.next
                head = head.next
        even.next = None
        odd.next = dummyEven.next
        return dummyOdd.next