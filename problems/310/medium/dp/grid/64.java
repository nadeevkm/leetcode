class SolutionBottomUp {
    public int minPathSum(int[][] grid) {
        int rc = grid.length;
        int cc = grid[0].length;
        for (int r = 0; r < rc; r++){
            for (int c = 0; c < cc; c++){
                int prevMin = r == 0 && c == 0 ? 0 : Math.min(
                        r == 0 ? Integer.MAX_VALUE : grid[r-1][c],
                        c == 0 ? Integer.MAX_VALUE : grid[r][c-1]
                );
                grid[r][c] = grid[r][c] + prevMin;
            }
        }
        return grid[rc - 1][cc - 1];
    }
}

class SolutionTopToBottom {

    int[][] mem;

    public int minPathSum(int[][] grid) {
        mem = new int[grid.length][grid[0].length];
        return dfs(0, 0, grid);
    }

    private int dfs(int r, int c, int[][] grid) {
        if (r >= grid.length || c >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (mem[r][c] != 0) {
            return mem[r][c];
        }
        int res;
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            res = grid[r][c];
        } else {
            res = grid[r][c] + Math.min(
                    dfs(r + 1, c, grid),
                    dfs(r, c + 1, grid)
            );
        }
        mem[r][c] = res;
        return res;
    }
}