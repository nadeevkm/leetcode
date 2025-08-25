import heapq


class SolutionQuickSelect:  # average O(n), worse O(n^2)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        targInd = len(nums) - k

        def partition(nums, st, end):
            # ri = random.randint(st, end)
            ri = st + (end - st) // 2
            nums[end], nums[ri] = nums[ri], nums[end]
            ins = st
            for i in range(st, end):
                if nums[i] < nums[end]:
                    nums[i], nums[ins] = nums[ins], nums[i]
                    ins += 1
            nums[ins], nums[end] = nums[end], nums[ins]
            return ins

        st = 0
        end = len(nums) - 1
        pi = -1
        while pi != targInd:
            pi = partition(nums, st, end)
            if pi < targInd:
                st = pi + 1
            elif pi > targInd:
                end = pi - 1

        return nums[pi]


class SolutionMaxHeap:  # O(n*logn)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        maxHeap = []
        for n in nums:
            heapq.heappush(maxHeap, -1 * n)
        count = 1
        while count != k:
            val = -1 * heapq.heappop(maxHeap)
            count += 1
        return -1 * heapq.heappop(maxHeap)


class SolutionMinHeap:  # O(n*logk)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        minHeap = []
        targetSize = k
        for n in nums:
            heapq.heappush(minHeap, n)
            if len(minHeap) > targetSize:
                heapq.heappop(minHeap)
        return heapq.heappop(minHeap)

