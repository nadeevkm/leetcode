class Solution {

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
        visited.add(currCell);
        if (board[r][c] != word.charAt(charInd)) {
            return false;
        }
        if (charInd == word.length() - 1) {
            return true;
        }
        var found = false;
        for (var d : dirs) {
            found = found || dfs(r + d[0], c + d[1], board, word, charInd + 1, visited);
        }
        return found;
    }

    record Cell(int r, int c) { }
}