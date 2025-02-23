class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()
        prevNum1 = None
        for p1 in range(len(nums) - 2):
            if nums[p1] == prevNum1:
                continue

            p2 = p1 + 1
            prevNum2 = None
            p3 = len(nums) -1
            prevNum3 = None
            while p2 < p3:
                if nums[p2] == prevNum2:
                    p2 += 1
                    continue
                if nums[p3] == prevNum3:
                    p3 -= 1
                    continue

                sum = nums[p1] + nums[p2] + nums[p3]
                if sum > 0:
                    # prevNum3 = nums[p3] - we can omit this, cause next iteration it still won't sum up to the desired target and won't get in the res
                    p3 -= 1
                elif sum < 0:
                    # prevNum2 = nums[p2]
                    p2 += 1
                else:
                    res.append((nums[p1], nums[p2], nums[p3]))
                    prevNum2 = nums[p2]
                    p2 += 1
                    # actually can move p3 here as well - cause it would be safe to not check prev p3 with any other p2

            prevNum1 = nums[p1]

        return res


