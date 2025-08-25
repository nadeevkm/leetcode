class Solution:
    def canJump(self, nums: List[int]) -> bool:
        p = 0
        front = nums[0]
        while p <= front:
            front = max(front, p + nums[p])
            if front >= len(nums) - 1:
                return True
            p += 1
        return False
