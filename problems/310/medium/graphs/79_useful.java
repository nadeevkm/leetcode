import java.util.*;

class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        boolean[][] usedCells = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                usedCells[r][c] = true; // Failed here !! Important to add
                if (findWord(r, c, board, word, 0, usedCells)) {
                    return true;
                }
                usedCells[r][c] = false;
            }
        }

        return false;
    }

    public boolean findWord(int r, int c, char[][] board, String word, int ci, boolean[][] usedCells) {

        int rows = board.length;
        int cols = board[0].length;

        char cell = board[r][c];

        if (cell != word.charAt(ci)) {
            return false;
        }

        if (ci == word.length() - 1) {
            return true;
        }

        for (int i = Math.max(0, r - 1); i <= Math.min(r + 1, rows - 1); i++) {
            for (int j = Math.max(0, c - 1); j <= Math.min(c + 1, cols - 1); j++) {
                if (i != r && j != c) {  // IMPORTANT - skip diagonals, should be only up, down, left, right
                    continue;
                }

                if (usedCells[i][j]) {
                    continue;
                }

                ci++;
                usedCells[i][j] = true;

                if (findWord(i, j, board, word, ci, usedCells)) {
                    return true;
                }

                usedCells[i][j] = false;
                ci--;
            }
        }

        return false;
    }
}


class Check {
    public static void main(String[] args) {
//        var pp = new Solution().exist(new char[][]{{'a', 'a'}}, "aaa");
//        var pp = new Solution().exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}}, "ABCCED");
        var pp = new Solution().exist(new char[][]{{'a'}}, "a");
        int q = 0;
    }
}


class SolutionRepetit {

    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        for(int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                if (board[r][c] == word.charAt(0)){
                    var found = dfs(r, c, board, word, 0, new HashSet<Cell>());
                    if (found){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, char[][] board, String word, int charInd, Set<Cell> visited) {
        var currCell = new Cell(r, c);
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || visited.contains(currCell)) {
            return false;
        }
        if (board[r][c] != word.charAt(charInd)) {
            return false;
        }
        if (charInd == word.length() - 1) {
            return true;
        }
        visited.add(currCell);
        var found = false;
        for (var d : dirs) {
            found = found || dfs(r + d[0], c + d[1], board, word, charInd + 1, visited);
        }
        visited.remove(currCell);
        return found;
    }

    record Cell(int r, int c) {
    }
}

class SolutionRepetitOptimal {

    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        for(int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                if (board[r][c] == word.charAt(0)){
                    var found = dfs(r, c, board, word, 0);
                    if (found){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, char[][] board, String word, int charInd) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == '*') {
            return false;
        }
        if (board[r][c] != word.charAt(charInd)) {
            return false;
        }
        if (charInd == word.length() - 1) {
            return true;
        }
        board[r][c] = '*'; // mark visited - can use same grid instead of separate Set, cause we'll revert that change after exiting
        var found = false;
        for (var d : dirs) {
            found = found || dfs(r + d[0], c + d[1], board, word, charInd + 1);
        }
        board[r][c] = word.charAt(charInd); // !!! clean visiting, like in backtracking (couse word can be formed by another path with the same cell)
        return found;
    }
}
