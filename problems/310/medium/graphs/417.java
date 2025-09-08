import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] pac = bfs(heights, 0, 0);
        int[][] atl = bfs(heights, rows - 1, cols - 1);
        List<List<Integer>> res = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pac[r][c] == 1 && atl[r][c] == 1) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        return res;
    }

    public int[][] bfs(int[][] heights, int initRow, int initCol) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] res = new int[rows][cols];
        Queue<int[]> q = new LinkedList<>();

        for (int c = 0; c < cols; c++) {
            q.add(new int[]{initRow, c});
            res[initRow][c] = 1;
        }
        for (int r = 0; r < rows; r++) {
            q.add(new int[]{r, initCol});
            res[r][initCol] = 1;
        }

        while (!q.isEmpty()) {
            var coord = q.poll();
            int row = coord[0];
            int col = coord[1];
            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

            for (var dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];

                if (r == -1 || r == rows || c == -1 || c == cols || res[r][c] == 1 || heights[r][c] < heights[row][col]) {
                    continue;
                }

                res[r][c] = 1;
                q.add(new int[]{r, c});
            }
        }
        return res;
    }
}


class SolutionRepetit {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        Deque<int[]> qPacif = new LinkedList<>();
        boolean[][] reachedPacif = new boolean[rows][cols];
        Deque<int[]> qAtl = new LinkedList<>();
        boolean[][] reachedAtl = new boolean[rows][cols];

        for (int c = 0; c < cols; c++){
            qPacif.add(new int[]{0, c});
            reachedPacif[0][c] = true;
            qAtl.add(new int[]{rows - 1, c});
            reachedAtl[rows - 1][c] = true;
        }
        qPacif.pollFirst();
        qAtl.pollLast();
        for (int r = 0; r < rows; r++){
            qPacif.add(new int[]{r, 0});
            reachedPacif[r][0] = true;
            qAtl.add(new int[]{r, cols - 1});
            reachedAtl[r][cols - 1] = true;
        }
        qPacif.pollFirst();
        qAtl.pollLast();

        bfs(qPacif, reachedPacif, heights);
        bfs(qAtl, reachedAtl, heights);

        List<List<Integer>> res = new LinkedList<>();
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (reachedPacif[r][c] && reachedAtl[r][c]){
                    res.add(Arrays.asList(r,c));
                }
            }
        }
        return res;
    }

    private void bfs(Deque<int[]> q, boolean[][] reached, int[][] heights){
        int rows = reached.length;
        int cols = reached[0].length;
        var dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        while (!q.isEmpty()){
            var curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            for (var dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !reached[nr][nc] && heights[nr][nc] >= heights[r][c]){
                    reached[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}

class Check {
    public static void main(String[] args) {

        var res = new Solution().pacificAtlantic(
                new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}
        );


    }
}