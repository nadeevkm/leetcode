class Solution:
    def numSquares(self, n: int) -> int:
        mem = [0] * (n + 1)
        sqRoot = 1
        for i in range(1, n + 1):
            if i == sqRoot * sqRoot:
                mem[i] = 1
                sqRoot += 1
            else:
                minVal = i
                for j in range(1, sqRoot):
                    minVal = min(minVal, mem[j * j] + mem[i - j * j])
                mem[i] = minVal
        return mem[n]

class SolutionConcise:
    def numSquares(self, n: int) -> int:
        mem = [0] * (n + 1)
        for i in range(1, n + 1):
            minVal = n
            j = 1
            while j * j <= i:
                # min = min(minVal, mem[j * j] + mem[i - j * j]); *** every mem[j*j] is actually '1'
                minVal = min(minVal, 1 + mem[i - j * j])
                j += 1
            mem[i] = minVal
        return mem[n]


Solution().numSquares(2)

