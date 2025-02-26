import java.util.*;

class Solution {
    public void solve(char[][] board) { // check only borders, adjacent points are not flippable, flip all others, track visits in place with different symbol
        if (board.length < 3 || board[0].length < 3) {
            return;
        }

        // upper, bottom row
        for (int r = 0; r < board.length; r = r + board.length - 1) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'X' || board[r][c] == '1') {
                    continue;
                }
                bfs(r, c, board);
            }
        }

        // left, right column
        for (int c = 0; c < board[0].length; c = c + board[0].length - 1) {
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] == 'X' || board[r][c] == '1') {
                    continue;
                }
                bfs(r, c, board);
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '1') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    public void bfs(int startRow, int startCol, char[][] board) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});
        board[startRow][startCol] = '1';
        while (!q.isEmpty()) {
            int[] point = q.poll();

            int[] up = new int[]{point[0] - 1, point[1]};
            int[] down = new int[]{point[0] + 1, point[1]};
            int[] left = new int[]{point[0], point[1] - 1};
            int[] right = new int[]{point[0], point[1] + 1};

            for (int[] candPoint : Arrays.asList(up, down, left, right)) {
                if (candPoint[0] < 0 || candPoint[0] > board.length - 1 || candPoint[1] < 0 || candPoint[1] > board[0].length - 1
                        || board[candPoint[0]][candPoint[1]] != 'O') {
                    continue;
                }
                q.add(candPoint);
                board[candPoint[0]][candPoint[1]] = '1';
            }
        }
    }
}

class Solution3 {
    public void solve(char[][] board) { // check only borders, adjacent points are not flippable, flip all others, use another matrix to track visits
        if (board.length < 3 || board[0].length < 3) {
            return;
        }

        int[][] map = new int[board.length][board[0].length];

        // upper, bottom row
        for (int r = 0; r < board.length; r = r + board.length - 1) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'X' || map[r][c] == 1) {
                    continue;
                }
                bfs(r, c, board, map);
            }
        }

        // left, right column
        for (int c = 0; c < board[0].length; c = c + board[0].length - 1) {
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] == 'X' || map[r][c] == 1) {
                    continue;
                }
                bfs(r, c, board, map);
            }
        }

        for (int r = 1; r < board.length - 1; r++) {
            for (int c = 1; c < board[0].length - 1; c++) {
                if (board[r][c] == 'X' || map[r][c] == 1) {
                    continue;
                }
                board[r][c] = 'X';
            }
        }
    }

    public void bfs(int startRow, int startCol, char[][] board, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});
        map[startRow][startCol] = 1;
        while (!q.isEmpty()) {
            int[] point = q.poll();

            int[] up = new int[]{point[0] - 1, point[1]};
            int[] down = new int[]{point[0] + 1, point[1]};
            int[] left = new int[]{point[0], point[1] - 1};
            int[] right = new int[]{point[0], point[1] + 1};

            for (int[] candPoint : Arrays.asList(up, down, left, right)) {
                if (candPoint[0] < 0 || candPoint[0] > board.length - 1 || candPoint[1] < 0 || candPoint[1] > board[0].length - 1
                        || board[candPoint[0]][candPoint[1]] != 'O' || map[candPoint[0]][candPoint[1]] == 1) {
                    continue;
                }
                q.add(candPoint);
                map[candPoint[0]][candPoint[1]] = 1;
            }
        }
    }
}

class Solution2 {
    public void solve(char[][] board) { // check only borders, adjacent points are not flippable, flip all others
        if (board.length < 3 || board[0].length < 3) {
            return;
        }

        Set<Point> notToFlip = new HashSet<>();

        // upper, bottom row
        for (int r = 0; r < board.length; r = r + board.length - 1) {
            for (int c = 0; c < board[0].length; c++) {
                Point point = new Point(r, c);
                char ch = board[r][c];

                if (ch == 'X' || notToFlip.contains(point)) {
                    continue;
                }

                notToFlip.addAll(bfs(point, board, board.length, board[0].length));
            }
        }

        // left, right column
        for (int c = 0; c < board[0].length; c = c + board[0].length - 1) {
            for (int r = 0; r < board.length; r++) {
                Point point = new Point(r, c);
                char ch = board[r][c];

                if (ch == 'X' || notToFlip.contains(point)) {
                    continue;
                }

                notToFlip.addAll(bfs(point, board, board.length, board[0].length));
            }
        }

        for (int r = 1; r < board.length - 1; r++) {
            for (int c = 1; c < board[0].length - 1; c++) {
                Point point = new Point(r, c);

                if (notToFlip.contains(point)) {
                    continue;
                }

                board[r][c] = 'X';
            }
        }
    }

    public Set<Point> bfs(Point start, char[][] board, int rows, int cols) {
//        Set<Point> visited = new HashSet<>();
        Set<Point> queued = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        queued.add(start);

        while (!q.isEmpty()) {
            Point point = q.poll();
//            visited.add(point);
            queued.add(point);

            // up
            if (point.row - 1 >= 0) {
                var nextPoint = new Point(point.row - 1, point.col);
                if (getChar(board, nextPoint) == 'O' && !queued.contains(nextPoint)) { // !visited.contains(nextPoint) &&
                    q.add(nextPoint);
                    queued.add(nextPoint);
                }
            }

            // bottom
            if (point.row + 1 <= rows - 1) {
                var nextPoint = new Point(point.row + 1, point.col);
                if (getChar(board, nextPoint) == 'O' && !queued.contains(nextPoint)) {
                    q.add(nextPoint);
                    queued.add(nextPoint);
                }
            }

            // left
            if (point.col - 1 >= 0) {
                var nextPoint = new Point(point.row, point.col - 1);
                if (getChar(board, nextPoint) == 'O' && !queued.contains(nextPoint)) {
                    q.add(nextPoint);
                    queued.add(nextPoint);
                }
            }


            // right
            if (point.col + 1 <= cols - 1) {
                var nextPoint = new Point(point.row, point.col + 1);
                if (getChar(board, nextPoint) == 'O' && !queued.contains(nextPoint)) {
                    q.add(nextPoint);
                    queued.add(nextPoint);
                }
            }
        }

//        return visited;
        return queued;
    }

    public char getChar(char[][] board, Point point) {
        return board[point.row][point.col];
    }

    class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}

/*
class Solution1 {  // brute force, all cells, check flip or not to flip

    public void solve(char[][] board) {
        Set<Point> toFlip = new HashSet<>();
        Set<Point> notToFlip = new HashSet<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                Point point = new Point(r, c);
                char ch = board[r][c];

                if (ch == 'X') {
                    continue;
                }
                if (notToFlip.contains(point)) {
                    continue;
                }
                if (toFlip.contains(point)) {
                    board[r][c] = 'X';
                    continue;
                }

                Object[] res = bfs(point, board, board.length, board[0].length);
                boolean flip = (boolean) res[0];
                Set<Point> visited = (Set<Point>) res[1];
                if (flip) {
                    board[r][c] = 'X';
                    toFlip.addAll(visited);
                } else {
                    notToFlip.addAll(visited);
                }
            }
        }
    }

    public Object[] bfs(Point start, char[][] board, int rows, int cols) {
        boolean res = true;
        Set<Point> visited = new HashSet<>();

        Queue<Point> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Point point = q.poll();
            visited.add(point);

            // char up = (point[0] - 1 < 0) : 'e' ? board[point[0] - 1][point[1]];
            if (point.row - 1 < 0) {
                res = false;
            } else {
                var nextPoint = new Point(point.row - 1, point.col);
                if (getChar(board, nextPoint) == 'O' && !visited.contains(nextPoint)){
                    q.add(nextPoint);
                }
            }

            // bottom
            if (point.row + 1 > rows - 1) {
                res = false;
            } else {
                var nextPoint = new Point(point.row + 1, point.col);
                if (getChar(board, nextPoint) == 'O' && !visited.contains(nextPoint)){
                    q.add(nextPoint);
                }
            }

            // left
            if (point.col - 1 < 0) {
                res = false;
            } else {
                var nextPoint = new Point(point.row, point.col - 1);
                if (getChar(board, nextPoint) == 'O' && !visited.contains(nextPoint)){
                    q.add(nextPoint);
                }
            }


            // right
            if (point.col + 1 > cols - 1) {
                res = false;
            } else {
                var nextPoint = new Point(point.row, point.col + 1);
                if (getChar(board, nextPoint) == 'O' && !visited.contains(nextPoint)){
                    q.add(nextPoint);
                }
            }
        }

        return new Object[]{res, visited};
    }

    public char getChar(char[][] board, Point point){
        return board[point.row][point.col];
    }

    class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
 */


class Check {

    public static void main(String[] args) {
//        var board = new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        var board = new char[][]
                {
                        {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'X', 'O', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O'}
                        , {'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X'}
                        , {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'O'}
                        , {'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'}
                        , {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
                };
        new Solution().solve(
                board
        );
        int p = 1;
    }
}


