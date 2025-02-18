class Solution:
    def findMin(self, nums: List[int]) -> int:
        l = 0
        r = len(nums) - 1
        while l < r:
            m = l + (r - l)//2
            if nums[l] < nums[r]:
                break
            elif nums[m] >= nums[l]:  # (including situations m == l) i.e. nums[m] is definately not a min
                l = m + 1
            else:  # nums[m] < nums[l]: , nums[m] can still be a min
                r = m
        return nums[l]
