class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        n = len(matrix)
        up = 0
        down = n - 1
        left = 0
        right = n - 1
        while up < down:
            for c in range(left, right):  # process rotation backwards to save space
                tmp = matrix[up][c]
                matrix[up][c] = matrix[n - 1 - c][left]
                matrix[n - 1 - c][left] = matrix[down][n - 1 - c]
                matrix[down][n - 1 - c] = matrix[c][right]
                matrix[c][right] = tmp
            left += 1
            right -= 1
            up += 1
            down -= 1
        
