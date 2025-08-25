# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = dummy = ListNode()
        curr = head

        while curr:
            while curr and curr.next and curr.next.val == curr.val:
                val = curr.val
                while curr and curr.val == val:
                    curr = curr.next

            prev.next = curr
            prev = prev.next
            curr = curr.next if curr else None

        return dummy.next


class SolutionTwo:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = dummy = ListNode()
        curr = head
        
        while curr:
            if curr and curr.next and curr.next.val == curr.val:
                dupVal = curr.val
                while curr and curr.val == dupVal:
                    curr = curr.next
            else:
                prev.next = curr
                prev = prev.next
                curr = curr.next
            
        prev.next = None
        return dummy.next
			
