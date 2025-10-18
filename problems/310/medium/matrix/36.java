import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int size = board.length;
        Set[] rows = new HashSet[size];
        Set[] cols = new HashSet[size];
        Set[][] subSquares = new HashSet[size / 3][size / 3];
        for (int r = 0; r < size; r++) {
            if (rows[r] == null) {
                rows[r] = new HashSet<Character>();
            }
            for (int c = 0; c < size; c++) {
                if (cols[c] == null) {
                    cols[c] = new HashSet<Character>();
                }
                if (subSquares[r / 3][c / 3] == null) {
                    subSquares[r / 3][c / 3] = new HashSet<Character>();
                }
                if (board[r][c] == '.'){
                    continue;
                }
                if (!rows[r].add(board[r][c]) || !cols[c].add(board[r][c]) || !subSquares[r / 3][c / 3].add(board[r][c])) {
                    return false;
                }
            }
        }
        return true;
    }
}

class SolutionRepetit {

    Set[] rows = new HashSet[9];
    Set[] cols = new HashSet[9];
    Set[][] zones = new HashSet[3][3];

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<Character>();
            cols[i] = new HashSet<Character>();
            zones[i / 3][i % 3] = new HashSet<Character>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                var num = board[r][c];
                if (num == '.'){
                    continue;
                }
                var newInRow = rows[r].add(num);
                var newInCol = cols[c].add(num);
                var newInZone = zones[r / 3][c / 3].add(num);
                if (!newInRow || !newInCol || !newInZone) {
                    return false;
                }
            }
        }

        return true;
    }
}