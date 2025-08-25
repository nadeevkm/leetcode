import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        if ((m == 0 && n == 0) || (m == 1 && n == 0) || (m == 0 && n == 1)) {
            return 1;
        }
        int[][] mem = new int[m][n];
        for (int c = 1; c < n; c++) {
            mem[0][c] = 1;
        }
        for (int r = 1; r < m; r++) {
            mem[r][0] = 1;
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                mem[r][c] = mem[r - 1][c] + mem[r][c - 1];
            }
        }
        return mem[m - 1][n - 1];
    }
}


class SolutionMemOptim { // actually we need only two rows, prev and curr, and we can switch 'em after each row fill
    public int uniquePaths(int m, int n) {
        int[] curr = new int[n];
        Arrays.fill(curr, 1);
        int[] prev = new int[n];
        Arrays.fill(prev,1);
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
               curr[c] = prev[c] + curr[c - 1];
            }
            int[] tmp = curr;
            curr = prev;
            prev = tmp;
        }
        return prev[n - 1];
    }
}
