import java.util.LinkedList;
import java.util.Queue;

// solved on Neetcode, same as 'Walls And Gates'
class Solution { // time complexity is hard to calculate, little bit more than O(m*n)
    public void islandsAndTreasure(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    bfs(grid, r, c);
                }
            }
        }
    }

    public void bfs(int[][] grid, int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});

        int level = 0;

        while (!q.isEmpty()) {
            int lSize = q.size();
            while (lSize-- > 0) {
                var coord = q.poll();
                int r = coord[0];
                int c = coord[1];

                //grid[r][c] = level;

                if (isSuitable(grid, r + 1, c, level + 1)) {
                    grid[r + 1][c] = level + 1;
                    q.add(new int[]{r + 1, c});
                }
                if (isSuitable(grid, r - 1, c, level + 1)) {
                    grid[r - 1][c] = level + 1;
                    q.add(new int[]{r - 1, c});
                }
                if (isSuitable(grid, r, c + 1, level + 1)) {
                    grid[r][c + 1] = level + 1;
                    q.add(new int[]{r, c + 1});
                }
                if (isSuitable(grid, r, c - 1, level + 1)) {
                    grid[r][c - 1] = level + 1;
                    q.add(new int[]{r, c - 1});
                }
            }
            level++;
        }
    }

    private boolean isSuitable(int[][] grid, int r, int c, int val) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length
                && grid[r][c] > val;
    }
}


class SolutionSimultaneous { // we can do it in more optimal way, in case we run our searches simultaneously from each treasure  - O(m*n)
//    public void islandsAndTreasure(int[][] grid) {
    public void wallsAndGates(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    q.add(new int[]{r, c});
                }
            }
        }

        int steps = 0;
        while (!q.isEmpty()) {
//            int levelSize = q.size();
//            while (levelSize-- > 0) {  // can do without level counting, just do grid[r][c] = grid[row][col] + 1;
                var coord = q.poll();
                int row = coord[0];
                int col = coord[1];

                int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
                for (var dir : dirs) {
                    int r = row + dir[0];
                    int c = col + dir[1];

                    if (r == -1 || r == rows || c == -1 || c == cols || grid[r][c] != Integer.MAX_VALUE) {
                        continue;
                    }

                    q.add(new int[]{r, c});
//                    grid[r][c] = steps + 1;
                    grid[r][c] = grid[row][col] + 1;
                }
            }
//            steps++;
//        }
    }
}


class SolutionRepetit {

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    q.add(new int[]{r, c});
                }
            }
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                var cell = q.poll();
                int r = cell[0];
                int c = cell[1];
                for (var d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols &&
                            rooms[nr][nc] == Integer.MAX_VALUE) {
                        rooms[nr][nc] = level + 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            level++;
        }
    }
}

