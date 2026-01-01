class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (matrix[m][0] == target) {
                return true;
            } else if (matrix[m][0] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (l == 0) {
            return false;
        }
        int row = l - 1;
        l = 0;
        r = matrix[0].length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (matrix[row][m] == target) {
                return true;
            } else if (matrix[row][m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }
}