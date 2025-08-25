import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] dist = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dist[r][c] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    dist[r][c] = 0;
                    q.add(new int[]{r, c});
                    break;
                }
            }
        }
        while (!q.isEmpty()) {
            var point = q.poll();
            int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (var d : dir) {
                int r = point[0] + d[0];
                int c = point[1] + d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols) {
                    continue;
                }
                int newDist = mat[r][c] == 0 ? 0 : dist[point[0]][point[1]] + 1;
                if (newDist >= dist[r][c]) {
                    continue;
                }
                dist[r][c] = newDist;
                q.add(new int[]{r, c});
            }
        }
        return dist;
    }
}


class SolutionOptimised { // add all 0 at the beginning while filling matrix with INFIN values
    // then we don't need to check dist! just check was it visited or not, cause due to BFS we will first visit nodes with the smallest possible distance
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] dist = new int[rows][cols];
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    q.add(new int[]{r, c});
                } else {
                    dist[r][c] = Integer.MAX_VALUE;
                }
            }
        }
        while (!q.isEmpty()) {
            var point = q.poll();
            int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (var d : dir) {
                int r = point[0] + d[0];
                int c = point[1] + d[1];
                if (r < 0 || r >= rows || c < 0 || c >= cols || dist[r][c] != Integer.MAX_VALUE) { // i.e. already visited
                    continue;
                }
                dist[r][c] = dist[point[0]][point[1]] + 1;
                q.add(new int[]{r, c});
            }
        }
        return dist;
    }
}

class SolutionLatest {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // lets code visited as non-negatives
        Deque<int[]> q = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    q.add(new int[]{r, c});
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        var dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int layer = 1;
        while (!q.isEmpty()) {
            int qs = q.size();

            while (qs > 0) {
                var curr = q.poll();
                var curR = curr[0];
                var curC = curr[1];

                for (var dir : dirs) {
                    var nR = curR + dir[0];
                    var nC = curC + dir[1];

                    if (nR >= 0 && nR < rows && nC >= 0 && nC < cols && mat[nR][nC] < 0) {
                        mat[nR][nC] = layer;
                        q.add(new int[]{nR, nC});
                    }
                }

                qs--;
            }
            layer++;
        }

        return mat;
    }
}