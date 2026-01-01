class Solution {

    int[][] dirs = new int[][]{{0,-1},{0,1},{1,0},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}};

    /*
    introducing 4 states (become(0/1) -> was(0/1)), encode them with 0-3

        0 <- 0  =00  0
        0 <- 1  =01  1
        1 <- 0  =10  2
        1 <- 1  =11  3

    */
    public void gameOfLife(int[][] board) {
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                var liveNeig = countNeig(r, c, board);
                if (board[r][c] == 1){
                    if (liveNeig == 2 || liveNeig == 3){ // 1 <- 1
                        board[r][c] = 3;
                    } else { // 0 <- 1
                        board[r][c] = 1;
                    }
                } else { // == 0
                    if (liveNeig == 3){ // 1 <- 0
                        board[r][c] = 2;
                    } else { // 0 <- 0
                        board[r][c] = 0;
                    }
                }
            }
        }

        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                board[r][c] = board[r][c] >> 1;
            }
        }
    }

    private int countNeig(int r, int c, int[][] board){
        int res = 0;
        for (var d : dirs){
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length){
                continue;
            }
            res += board[nr][nc] & 1;
        }
        return res;
    }
}