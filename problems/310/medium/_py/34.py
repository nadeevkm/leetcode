class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        def findFirst():
            l = 0
            r = len(nums) - 1
            while l < r:
                m = l + (r - l)//2
                if nums[m] == target:
                    r = m
                elif nums[m] < target:
                    l = m + 1
                else:
                    r = m - 1
            if l < 0 or l >= len(nums) or nums[l] != target:
                return -1
            return l

        def findLast():
            l = 0 # actually we can reuse l from prev search !, that will speed evrth up
            r = len(nums) - 1
            while l < r:
                m = r - (r - l)//2
                if nums[m] == target:
                    l = m
                elif nums[m] < target:
                    l = m + 1
                else: 
                    r = m - 1
            if l < 0 or l >= len(nums) or nums[l] != target:
                return -1
            return l

        
        return [findFirst(), findLast()]
