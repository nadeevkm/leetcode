class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        n = len(nums)
        count = 0
        p = 0
        while count != n:
            insert = (p + k) % n  # basically it is (len(nums) + k) % len(nums), which can be simplified
            num = nums[p]
            while insert != p:
                tmp = nums[insert]
                nums[insert] = num
                count += 1
                insert = (insert + k) % n
                num = tmp
            nums[insert] = num
            count += 1
            p += 1
           
