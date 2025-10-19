class SolutionRepetit {

    Cell[][] mem;

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        mem = new Cell[rows][cols];
        int res = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                var cellLeft = getMemCell(r, c - 1);
                var cellAbove = getMemCell(r - 1, c);
                var cellDiag = getMemCell(r - 1, c - 1);
                if (matrix[r][c] == '1') {
                    int horSide = 1 + cellLeft.onesToLeft();
                    int vertSide = 1 + cellAbove.onesToUp();
                    int sqSide = 1 + Math.min(cellDiag.sqSide(), Math.min(cellAbove.onesToUp(), cellLeft.onesToLeft()));
                    mem[r][c] = new Cell(horSide, vertSide, sqSide);
                    res = Math.max(res, sqSide * sqSide);
                } else {
                    mem[r][c] = new Cell(0, 0, 0);
                }
            }
        }
        return res;
    }

    private Cell getMemCell(int r, int c) {
        if (r < 0 || c < 0) {
            return new Cell(0, 0, 0);
        }
        return mem[r][c];
    }

    record Cell(int onesToLeft, int onesToUp, int sqSide) { }
}

class SolutionOptim {

    public int maximalSquareOptim(char[][] matrix) {  // actually we can base our decision only on a squares length
        int rc = matrix.length;
        int cc = matrix[0].length;
        int[][] mem = new int[rc][cc];
        int currVal = matrix[0][0] == '1' ? 1 : 0;
        mem[0][0] = currVal;
        int maxSquare = mem[0][0];
        for (int c = 1; c < cc; c++) {
            currVal = matrix[0][c] == '1' ? 1 : 0;
            if (currVal == 1) {
                mem[0][c] = 1;
                maxSquare = currVal;
            }
        }
        for (int r = 1; r < rc; r++) {
            currVal = matrix[r][0] == '1' ? 1 : 0;
            if (currVal == 1) {
                mem[r][0] = 1;
                maxSquare = currVal;
            }
        }
        for (int r = 1; r < rc; r++) {
            for (int c = 1; c < cc; c++) {
                currVal = matrix[r][c] == '1' ? 1 : 0;
                if (currVal == 1) {
                    int minEdge = Math.min(mem[r - 1][c - 1], Math.min(mem[r][c - 1], mem[r - 1][c]));
                    mem[r][c] = minEdge + 1;
                    maxSquare = Math.max(maxSquare, mem[r][c]);
                }
            }
        }
        return maxSquare * maxSquare;
    }
}
