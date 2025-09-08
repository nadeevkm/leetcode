class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid.length == 0) {
            return res;
        }
        int[][] map = new int[grid.length][grid[0].length]; // can track visits in-place instead, using 0 symbol

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1' && map[r][c] != 1) {
                    res++;
                    dfs(r, c, grid, map);
                }
            }
        }

        return res;
    }

    public void dfs(int r, int c, char[][] grid, int[][] map) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || map[r][c] == 1 || grid[r][c] != '1') {
            return;
        } else {
            map[r][c] = 1;
            dfs(r - 1, c, grid, map);
            dfs(r + 1, c, grid, map);
            dfs(r, c - 1, grid, map);
            dfs(r, c + 1, grid, map);
        }
    }
}

class SolutionRepetit {
    
    int rows;
    int cols;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int count = 0;
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (grid[r][c] == '1'){
                    count++;
                    dfs(r,c, grid);
                }
            } 
        }
        return count;
    }

    private void dfs(int r, int c, char[][] grid){
        if(r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0'){
            return;
        }

        grid[r][c] = '0';

        dfs(r - 1, c, grid);
        dfs(r + 1, c, grid);
        dfs(r, c - 1, grid);
        dfs(r, c + 1, grid);
    }
}

class Check {

    public static void main(String[] args) {
        var board = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        new Solution().numIslands(board);
        int p = 1;
    }
}