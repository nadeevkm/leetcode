import java.util.Deque;
import java.util.LinkedList;

class Solution {

    private int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

    public int getFood(char[][] grid) {
        Deque<int[]> q = new LinkedList<>();
        boolean found = false;
        for (int r = 0; r < grid.length && !found; r++){
            for (int c = 0; c < grid[0].length;c++){
                if (grid[r][c] == '*'){
                    grid[r][c] = 'X';
                    q.add(new int[]{r, c});
                    found = true; // no reason to proceed further
                    break;
                }
            }
        }
        int steps = 1;
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                var curr = q.poll();
                for (var d : dirs){
                    int r = curr[0] + d[0];
                    int c = curr[1] + d[1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'X'){
                        if (grid[r][c] == '#'){
                            return steps;
                        }
                        grid[r][c] = 'X';
                        q.add(new int[]{r, c});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}