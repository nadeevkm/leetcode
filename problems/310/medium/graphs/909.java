import java.util.*;

class SolutionBfs {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int [] marked = new int[n*n + 1];
        marked[1] = 1;

        int level = 0;
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0) {
                int cellNum = q.poll();
                if (cellNum == n*n){
                    return level;
                }

                for (int i = 1; i <= 6; i++){
                    int move = cellNum + i;
                    if (move > n*n){
                        continue;
                    }

                    int shortcut = getCell(move, board, n);
                    if (shortcut != -1){
                        move = shortcut;
                    }

                    if (marked[move] != 1) {
                        q.add(move);
                        marked[move] = 1;
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public int getCell(int cellNum, int[][] board, int n){
        int row = n - 1 - (cellNum-1)/n;
        int col;
        if ((n-row)%2 == 1){
            col = (cellNum-1)%n;
        } else {
            col = n - 1 - (cellNum-1)%n;
        }
        return board[row][col];
    }
}

class SolutionDfs {
    int minMoves;

    public int snakesAndLadders(int[][] board) {
        minMoves = Integer.MAX_VALUE;
        dfs(board, board.length * board.length, board.length, 1, 0, new HashMap<>());
        return minMoves == Integer.MAX_VALUE ? -1 : minMoves;
    }

    private void dfs(int[][] board, int maxPos, int sideSize, int currPos, int movesMade, Map<Integer, Integer> visited) {
        if (movesMade >= minMoves || (visited.containsKey(currPos) && movesMade >= visited.get(currPos))) {
            return;
        }
        if (currPos == maxPos) {
            minMoves = movesMade; // as we check (movesMade >= minMoves) in the beginning => we can safely replace minMoves here
            return;
        }
        visited.put(currPos, movesMade);
        for (int i = 1; i <= Math.min(6, maxPos - currPos); i++) { // in case we close to maxPosition we may not be able to do all range of movements (1-6)
            int nextPos = currPos + i;
            int row = sideSize - 1 - (nextPos - 1) / sideSize;
            int col = (sideSize - row) % 2 == 1 ? (nextPos - 1) % sideSize : sideSize - 1 - ((nextPos - 1) % sideSize);
            // in case board[row][col] != 1 then board[row][col] is destination
            dfs(board, maxPos, sideSize, board[row][col] == -1 ? nextPos : board[row][col], movesMade + 1, visited);
        }
    }
}