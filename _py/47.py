from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        res: List[List[int]] = list()

        counts = dict()
        for n in nums:
            if n not in counts:
                counts[n] = 1
            else:
                counts[n] += 1

        def permRec(counts, current):
            if len(current) == len(nums):
                res.append(current.copy())
                return

            for val in counts:
                if counts[val]:
                    current.append(val)
                    counts[val] = counts[val] - 1
                    permRec(counts, current)
                    current.pop()
                    counts[val] = counts[val] + 1

        permRec(counts, list())

        return res


Solution().permuteUnique([1,1,2])
