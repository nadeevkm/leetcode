class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        char[][] matrix = new char[numRows][s.length()]; //can be improved to (int) Math.ceil(n / (2 * numRows - 2.0));
        char[] str = s.toCharArray();

        int curr = 0;
        int col = 0;
        while (curr < s.length()){
            int row = 0;
            while(curr < s.length() && row < numRows){
                matrix[row][col] = str[curr];
                curr++;
                row++;
            }

            col++;
            row = numRows - 2;

            while(curr < s.length() && row > 0){
                matrix[row][col] = str[curr];
                curr++;
                col++;
                row--;
            }
        }

        curr = 0;
        char[] zigzag = new char[s.length()];
        for (int r = 0; r < numRows; r++){
            for(int c = 0; c < s.length(); c++){
                if (matrix[r][c] == '\u0000'){
                    continue;
                }
                zigzag[curr] = matrix[r][c];
                curr++;
            }
        }

        return new String(zigzag);
    }
}