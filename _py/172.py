class Solution:
    def trailingZeroes(self, n: int) -> int:
        count = 0
        mem = [0] * (n + 1)
        for i in range(1, n + 1):
            # if i % 10 == 0:
            #     mem[i] = 1 + mem[i // 10]
            #     count += mem[i]
            # elif i % 5 == 0:  <- actually we can apply similar logic just to the %5
            if i % 5 == 0:
                mem[i] = 1 + mem[i // 5]
                count += mem[i]
        return count


class SolutionLog5:
    def trailingZeroes(self, n: int) -> int:
        count = 0
        while n != 0:
            n = n // 5
            count += n
        return n
