import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    int[][] dirs = new int[][]{{-2, 1}, {-2, -1}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        var target = new Point(x, y);
        Queue<Point> q = new LinkedList<>();
        Set<Point> reached = new HashSet<>();
        q.add(new Point(0, 0));
        reached.add(new Point(0, 0));
        int steps = 0;
        while (true) {
            int size = q.size();
            while (size-- > 0) {
                var curr = q.poll();
                if (curr.equals(target)) {
                    return steps;
                }
                for (var d : dirs) {
                    int nx = curr.x() + d[0];
                    int ny = curr.y() + d[1];
                    if (nx < -2 || ny < -2 || nx > x + 2 || ny > y + 2) {
                        continue;
                    }
                    var next = new Point(nx, ny);
                    if (!reached.contains(next)) {
                        q.add(next);
                        reached.add(next);
                    }
                }
            }
            steps++;
        }

        //return -1;
    }

    record Point(int x, int y) { }

}
