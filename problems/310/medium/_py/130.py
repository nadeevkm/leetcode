class SolutionBfs:
    def solve(self, board: List[List[str]]) -> None:
        rows = len(board)
        cols = len(board[0])
        reached = set()
        q = deque()  # q - for current layer

        for c in range(cols):
            if board[0][c] == "O":
                reached.add((0, c))
                q.append((0, c))
            if board[rows - 1][c] == "O":
                reached.add((rows - 1, c))
                q.append((rows - 1, c))
        for r in range(1, rows - 1):
            if board[r][0] == "O":
                reached.add((r,0))
                q.append((r,0))
            if board[r][cols-1] == "O":
                reached.add((r, cols - 1))
                q.append((r, cols - 1))

        dirs = [[1,0],[-1,0],[0,1],[0,-1]]
        while q:
            prow, pcol = q.popleft()
            for d in dirs:
                drow = prow + d[0]
                dcol = pcol + d[1]
                if 0 <= dcol < cols and 0 <= drow < rows and board[drow][dcol] == "O" and (drow, dcol) not in reached:
                    reached.add((drow, dcol))
                    q.append((drow, dcol))

        
        for r in range(rows):
            for c in range(cols):
                if board[r][c] == "O" and (r, c) not in reached:  # reached == conected to board edges, => not surrounded
                    board[r][c] = "X"


class SolutionDfs:  #more concise, cause in BFS we repeat a lot of strings to preprocess (add to queue and reached) before the start
    def solve(self, board: List[List[str]]) -> None:
        rows = len(board)
        cols = len(board[0])
        visited = set() # we'll make dfs from 0 at the border, so all visited will be non-surrounded cells, others should be surrounded
        # can use special symbol for visited instead
        
        dirs = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        
        def dfs(row, col):
            if row < 0 or row >= rows or col < 0 or col >= cols or board[row][col] == "X" or (row, col) in visited:
                return
            visited.add((row, col))
            for d in dirs:
                dfs(row + d[0], col + d[1])

        for c in range(cols):
            if board[0][c] == "O":
                dfs(0, c)
            if board[rows - 1][c] == "O":
                dfs(rows - 1, c)
        for r in range(1, rows - 1):
            if board[r][0] == "O":
                dfs(r, 0)
            if board[r][cols - 1] == "O":
                dfs(r, cols - 1)

        for r in range(rows):
            for c in range(cols):
                if board[r][c] == "O" and (r, c) not in visited:
                    board[r][c] = "X"

        


        
