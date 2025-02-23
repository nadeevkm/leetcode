from typing import List


class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        rows = len(obstacleGrid)
        cols = len(obstacleGrid[0])
        mem = [[0 for c in range(cols)] for r in range(rows)]
        # mem = [i[:] for i in [[0] * cols] * rows]
        mem[0][0] = 1 if obstacleGrid[0][0] == 0 else 0
        for r in range(rows):
            for c in range(cols):
                if r == 0 and c == 0:
                    continue
                elif obstacleGrid[r][c] == 1:
                    mem[r][c] = 0
                else:
                    up = 0 if r == 0 else mem[r - 1][c]
                    left = 0 if c == 0 else mem[r][c - 1]
                    mem[r][c] = up + left
        return mem[rows - 1][cols - 1]


class SolutionMemoryCompression:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        rows = len(obstacleGrid)
        cols = len(obstacleGrid[0])
        mem = [0] * cols
        for r in range(rows):
            for c in range(cols):
                if obstacleGrid[r][c] == 1:
                    mem[c] = 0
                else:
                    up = 0 if r == 0 else mem[c]
                    left = 0 if c == 0 else mem[c - 1]
                    mem[c] = up + left if (c != 0 or r != 0) else 1
        return mem[cols - 1]

