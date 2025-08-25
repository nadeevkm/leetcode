class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []

        def dfs(perm, used):
            if len(perm) == len(nums):
                res.append(perm.copy())
                return

            for i in range(len(nums)):
                if i not in used:
                    perm.append(nums[i])
                    used.add(i)
                    dfs(perm, used)
                    used.remove(i)
                    perm.pop()

        dfs(list(), set())
        return res

