class Solution {

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
        while (!q.isEmpty()) {
            int level = 0;
            int size = q.size();
            while (--size > 0) {
                var cell = q.poll();
                int r = cell[0];
                int c = cell[1];
                rooms[nr][nc] = level;
                for (var d : dirs) {
                    int nr = r + d[0];
                    int nc = r + d[1];
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols &&
                            rooms[nr][nc] == Integer.MAX_VALUE) {
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            level++;
        }
    }
}