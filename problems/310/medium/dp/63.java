class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) { // use grid as a dp-table
        int rc = obstacleGrid.length;
        int cc = obstacleGrid[0].length;
        for (int r = 0; r < rc; r++){
            for (int c = 0; c < cc; c++){
                if (obstacleGrid[r][c] == 1){
                    obstacleGrid[r][c] = 0;
                } else {
                    int upPathCount = r == 0 ? 0 : obstacleGrid[r - 1][c];
                    int leftPathCount = c == 0 ? 0 : obstacleGrid[r][c - 1];
                    obstacleGrid[r][c] = r == 0 && c == 0 ? 1 : upPathCount + leftPathCount;
                }
            }
        }
        return obstacleGrid[rc - 1][cc - 1];
    }
}

class Solution2 { // without modifying grid
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rc = obstacleGrid.length;
        int cc = obstacleGrid[0].length;
        int[] mem = new int[cc];
        for (int r = 0; r < rc; r++){
            for (int c = 0; c < cc; c++){
                if (obstacleGrid[r][c] == 1){
                    mem[c] = 0;
                } else {
                    int upPathCount = r == 0 ? 0 : mem[c];
                    int leftPathCount = c == 0 ? 0 : mem[c - 1];
                    mem[c] = r == 0 && c == 0 ? 1 : upPathCount + leftPathCount;
                }
            }
        }
        return mem[cc - 1];
    }
}
