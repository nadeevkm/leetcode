from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class CycleDetection:
    def findCycle(self, head: ListNode) -> Optional[ListNode]:
        pSlow = head
        pFast = head
        hasCycle = False
        while pFast is not None and pFast.next is not None:
            pSlow = pSlow.next
            pFast = pFast.next.next
            if pSlow == pFast:
                hasCycle = True
                break
        if not hasCycle:
            return None
        pSlow = head
        while pSlow != pFast:
            pSlow = pSlow.next
            pFast = pFast.next
        return pSlow