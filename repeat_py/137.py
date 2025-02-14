class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        for b in range(32):
            resBit = 0
            for num in nums:
                currBit = (num >> b) & 1
                resBit += currBit
            ans += (resBit % 3) << b
        # check and consider sign-bit in python num representation
        if ans & 1 << 31:  # i.e. != 0
            ans -= 2 ** 32
        return ans

