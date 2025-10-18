import java.util.LinkedList;
import java.util.Queue;

class Solution { // BFS

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != 0) {
                    int area = bfs(grid, r, c);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    public int bfs(int[][] grid, int row, int col) {
        int area = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        grid[row][col] = 0;

        while (!q.isEmpty()) {
            var coord = q.poll();
            var r = coord[0];
            var c = coord[1];

            area++;
            grid[r][c] = 0;

            if (validGrid(grid, r - 1, c)) {
                q.add(new int[]{r - 1, c});
            }
            if (validGrid(grid, r + 1, c)) {
                q.add(new int[]{r + 1, c});
            }
            if (validGrid(grid, r, c + 1)) {
                q.add(new int[]{r, c + 1});
            }
            if (validGrid(grid, r, c - 1)) {
                q.add(new int[]{r, c - 1});
            }
        }
        return area;
    }

    private boolean validGrid(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1;
    }
}

class SolutionDFS { // DFS - more concise

    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if(grid[r][c] == 1){
                    int size = dfs(grid, r, c);
                    res = Math.max(res, size);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int r, int c){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = -1;

        int size = 1;
        for (int[] dir : dirs){
            size += dfs(grid, r + dir[0], c + dir[1]);
        }
        return size;
    }
}


class SolutionIterativeWithStack {

    int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    Deque<int[]> st = new LinkedList<>();

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int size = 0;
                    st.push(new int[] { r, c });
                    while (!st.isEmpty()) {
                        int[] point = st.pop();
                        int cr = point[0];
                        int cc = point[1];
                        if (cr < 0 || cr >= grid.length || cc < 0 || cc >= grid[0].length || grid[cr][cc] != 1) {
                            continue;
                        }
                        size += 1;
                        grid[cr][cc] = 0;
                        for (int[] dir : dirs) {
                            st.push(new int[] { cr + dir[0], cc + dir[1] });
                        }
                    }
                    res = Math.max(res, size);
                }
            }
        }
        return res;
    }
}