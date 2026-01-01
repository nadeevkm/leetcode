class Solution {

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