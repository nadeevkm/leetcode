class Solution {
    public void rotate(int[][] matrix) {
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix.length - 1;

        while ( top < bottom) {
            for (int i = 0; i < right - left; i++){
                var topCandPoint = new Point(top, left + i);
                var leftCandPoint = new Point(bottom - i,left);
                swap(matrix, topCandPoint, leftCandPoint);
                var bottomCandPoint = new Point(bottom, right - i);
                swap(matrix, leftCandPoint, bottomCandPoint);
                var rightCandPoint = new Point(left + i, right);
                swap(matrix,bottomCandPoint, rightCandPoint);
            }
            top++;
            bottom--;
            left++;
            right--;
        }
    }

    class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public void swap(int [][] matrix, Point firstPoint, Point secondPoint){
        matrix[firstPoint.row][firstPoint.col] =
                matrix[firstPoint.row][firstPoint.col] ^ matrix[secondPoint.row][secondPoint.col];

        matrix[secondPoint.row][secondPoint.col]
                = matrix[firstPoint.row][firstPoint.col] ^ matrix[secondPoint.row][secondPoint.col];

        matrix[firstPoint.row][firstPoint.col] =
                matrix[firstPoint.row][firstPoint.col] ^ matrix[secondPoint.row][secondPoint.col];
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();

        var matrix1 = new int [][]{
                {1,2},
                {3,4}
        };
//        var matrix2 = new int [][]{{1,2,3},{4,5,6},{7,8,9}};
        var matrix3 = new int [][]{
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16}
        };
        sol.rotate(matrix3);
    }
}
