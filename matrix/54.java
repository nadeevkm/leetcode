import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> spiralOrder(int[][] matrix) { // more concise and easy to reproduce

        var numsOrder = new ArrayList<Integer>();

        if (matrix.length == 0 || matrix[0].length == 0){
            return numsOrder;
        }

        int top = 0;
        int bottom = matrix.length;
        int left = 0;
        int right = matrix[0].length;

        while (true){
            for (int i = left; i < right; i++){
                numsOrder.add(matrix[top][i]);
            }
            top++;
            if (top > bottom - 1){
                break;
            }

            for (int i = top; i < bottom; i++){
                numsOrder.add(matrix[i][right - 1]);
            }
            right--;
            if (left > right - 1){
                break;
            }

            for (int i = right - 1; i >= left; i--){
                numsOrder.add(matrix[bottom - 1][i]);
            }
            bottom--;
            if (top > bottom - 1){
                break;
            }

            for (int i = bottom - 1; i >= top; i--){
                numsOrder.add(matrix[i][left]);
            }
            left++;
            if (left > right - 1){
                break;
            }
        }
        return numsOrder;
    }



    public List<Integer> spiralOrder1(int[][] matrix) { // my first version

        var numsOrder = new ArrayList<Integer>();

        if (matrix.length == 0){
            return numsOrder;
        }

        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        int topRows = 0;
        int bottRow = 0;
        int leftCol = 0;
        int rightCol = 0;

        while (true){
            var topLeftPoint = new Point(topRows, leftCol);
            if (topLeftPoint.col >= colNum - leftCol){
                return numsOrder;
            }
            for (int i = topLeftPoint.col; i < colNum - leftCol; i++){
                numsOrder.add(matrix[topLeftPoint.row][i]);
            }
            topRows++;

            var topRightPoint = new Point(topRows, colNum - rightCol - 1);
            if (topRightPoint.row >= rowNum - bottRow) {
                return numsOrder;
            }
            for (int i = topRightPoint.row; i < rowNum - bottRow; i++){
                numsOrder.add(matrix[i][topRightPoint.col]);
            }
            rightCol++;

            var botRightPoint = new Point(rowNum - bottRow - 1, colNum - rightCol - 1);
            if (botRightPoint.col < leftCol){
                return numsOrder;
            }
            for (int i = botRightPoint.col; i >= leftCol; i--){
                numsOrder.add(matrix[botRightPoint.row][i]);
            }
            bottRow++;

            var botLeftPoint = new Point(rowNum - bottRow - 1, leftCol);
            if(botLeftPoint.row < topRows){
                return numsOrder;
            }
            for (int i = botLeftPoint.row; i >= topRows; i--){
                numsOrder.add(matrix[i][botLeftPoint.col]);
            }
            leftCol++;
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
}
