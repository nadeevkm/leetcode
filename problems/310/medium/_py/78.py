class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(subset, size, st):
            if len(subset) == size:
                res.append(subset.copy())
                return
            
            for i in range(st, len(nums)):
                n = nums[i]                
                subset.append(n)
                dfs(subset, size, i + 1)
                subset.pop() 

        for s in range(len(nums) + 1):
            dfs([], s, 0)
        
        return res
