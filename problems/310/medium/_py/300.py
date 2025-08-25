class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        mem = [0] * n
        mem[0] = 1
        maxLen = 1
        for i in range(1, n):
            maxCurLen = 1
            for j in range(0, i):
                if nums[i] > nums[j]:
                    maxCurLen = max(maxCurLen, mem[j] + 1)
            mem[i] = maxCurLen
            maxLen = max(maxLen, mem[i])
        return maxLen


class SolutionBinarySearch:  # O(n*logn), idea is to maintain incrSub array, which holds the size of the LIS so far
    def lengthOfLIS(self, nums: List[int]) -> int:
        def binarySearch(subs, target):
            l = 0
            r = len(subs) - 1
            while l <= r:
                m = l + (r - l) // 2
                if subs[m] < target:
                    l = m + 1
                elif subs[m] > target:
                    r = m - 1
                else:
                    return m
            return l

        incrSub = []
        for i in range(0, len(nums)):
            ind = binarySearch(incrSub, nums[i]) # can use build-in python bisect_left func
            if ind == len(incrSub):
                incrSub.append(nums[i])
            else:
                incrSub[ind] = nums[i]
        return len(incrSub)

