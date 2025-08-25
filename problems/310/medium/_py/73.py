class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        # mem = O(1), in place with two additional vars
        rows = len(matrix)
        cols = len(matrix[0])
        row0 = col0 = matrix[0][0]
        # encode row0 and row0 as an indcator for that row 0 and col 0 should be all 0
        for c in range(1, cols):
            if matrix[0][c] == 0:
                col0 = 0
        for r in range(1, rows):
            if matrix[r][0] == 0:
                row0 = 0
        # encode cells in first row and first col as an indicators that whole according row and col should be 0
        for r in range(1, rows):
            for c in range(1, cols):
                if matrix[r][c] == 0:
                    matrix[0][c] = 0
                    matrix[r][0] = 0
        # fill zeroes for each row and col which is specified in first row and col
        for c in range(1, cols):
            if matrix[0][c] == 0:
                for r in range(1, rows):
                    matrix[r][c] = 0
        for r in range(1, rows):
            if matrix[r][0] == 0:
                for c in range(1, cols):
                    matrix[r][c] = 0
        # check if first col or row should be all filled with zeroes 
        if row0 == 0:
            for r in range(0, rows):
                matrix[r][0] = 0
        if col0 == 0:
            for c in range(0, cols):
                matrix[0][c] = 0
        
