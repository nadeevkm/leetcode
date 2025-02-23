from typing import List


class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        pSlow = 0
        pFast = 0
        while True:
            pSlow = nums[pSlow]
            pFast = nums[nums[pFast]]
            if pSlow == pFast:
                break
        pSlow = 0
        while nums[pSlow] != nums[pFast]:
            pSlow = nums[pSlow]
            pFast = nums[pFast]
        return nums[pSlow]


# CHECK
nums = [3,1,3,4,2]
Solution().findDuplicate(nums)
