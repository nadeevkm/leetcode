class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0){
            return;
        }
        int rowsNum = matrix.length;
        int colsNum = matrix[0].length;

        boolean firstRow = false;
        boolean firstCol = false;
        for (int r = 0; r < rowsNum; r++){
            for (int c = 0; c < colsNum; c++){
                if (matrix[r][c] == 0){
                    if (r == 0) firstRow = true;
                    if (c == 0) firstCol = true;
                    matrix[0][c] = 0;
                    matrix[r][0] = 0;
                }
            }
        }

        for (int r = 1; r < rowsNum; r++){
            if (matrix[r][0] == 0){
                for (int c = 0; c < colsNum; c++){
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c = 1; c < colsNum; c++){
            if (matrix[0][c] == 0){
                for (int r = 0; r < rowsNum; r++){
                    matrix[r][c] = 0;
                }
            }
        }

        if (firstRow) {
            for (int c = 0; c < colsNum; c++) {
                matrix[0][c] = 0;
            }
        }
        if (firstCol){
            for (int r = 0; r < rowsNum; r++){
                matrix[r][0] = 0;
            }
        }
    }
}
