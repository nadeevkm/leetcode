class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        up = 0
        down = len(matrix) - 1
        left = 0
        right = len(matrix[0]) - 1
        mode = 0
        while left <= right and up <= down:
            if mode == 0:
                for c in range(left, right + 1):
                    res.append(matrix[up][c])
                up += 1
            elif mode == 1:
                for r in range(up, down + 1):
                    res.append(matrix[r][right])
                right -= 1
            elif mode == 2:
                for c in range(right, left - 1, -1):
                    res.append(matrix[down][c])
                down -= 1
            elif mode == 3:
                for r in range(down, up - 1, -1):
                    res.append(matrix[r][left])
                left += 1
            mode = (mode + 1) % 4
        return res
