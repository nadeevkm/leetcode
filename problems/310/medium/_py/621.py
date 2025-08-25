import heapq
from collections import Counter, deque
from typing import List


class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        count = Counter(tasks)
        maxHeap = []
        for t, c in count.items():
            heapq.heappush(maxHeap, (-1 * c, t))  # actually don't need to keep task after counting, only the number itself

        cooldown = deque()
        interv = 0
        while maxHeap or cooldown:
            if maxHeap:
                count, task = heapq.heappop(maxHeap)
                count = -count - 1
                if count > 0:
                    cooldown.append((count, task, interv + n))

            while cooldown and cooldown[0][2] <= interv:
                count, task, _ = cooldown.popleft()
                heapq.heappush(maxHeap, (-1 * count, task))

            interv += 1

        return interv


Solution().leastInterval(["A","C","A","B","D","B"], 1)

