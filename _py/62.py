class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        mem = [[0 for c in range(n)] for r in range(m)]
        for c in range(n):
            mem[0][c] = 1
        for r in range(m):
            mem[r][0] = 1
        for r in range(1, m):
            for c in range(1, n):
                mem[r][c] = mem[r - 1][c] + mem[r][c - 1]
        return mem[m - 1][n - 1]
