class Solution {

    int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,1},{0, -1}};

    public int orangesRotting(int[][] grid) {
        Deque<int[]> q = new LinkedList<>();
        int fresh = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] == 1){
                    fresh++;
                } else if (grid[r][c] == 2){
                    q.add(new int[]{r, c});
                }
            }
        }

        if (fresh == 0){
            return 0;
        }

        int minute = -1;
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                var curr = q.poll();
                for (var d : dirs){
                    var nextR = curr[0] + d[0];
                    var nextC = curr[1] + d[1];
                    if (nextR >= 0 && nextR < grid.length && nextC >= 0 && nextC < grid[0].length && grid[nextR][nextC] == 1){
                        grid[nextR][nextC] = 2;
                        q.add(new int[]{nextR, nextC});
                        fresh--;
                    }
                }
            }
            minute++;
        }
        return fresh == 0 ? minute : -1;
    }
}