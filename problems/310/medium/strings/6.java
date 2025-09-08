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

class SolutionRepetition {
    public String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        var res = new StringBuilder();
        int blockLen = numRows - 2;
        int p1 = 0;
        while (p1 < s.length()){
            res.append(s.charAt(p1));
            p1 += numRows + blockLen;
        }
        for (int r = 1; r < numRows - 1; r++){
            p1 = r;
            while ( p1 < s.length()){
                res.append(s.charAt(p1));
                int p2 = p1 + (numRows - 1 - r)*2;
                if (p2 < s.length()){
                    res.append(s.charAt(p2));
                }
                p1 = p1 + numRows + blockLen;
            }
        }
        p1 = numRows - 1;
        while (p1 < s.length()){
            res.append(s.charAt(p1));
            p1 += numRows + blockLen;
        }
        return res.toString();
    }
}

class SolutionConcise { // can join all three cycles in a one with additional checks for the first and last row
    public String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        var res = new StringBuilder();
        int blockLen = numRows - 2;
        for (int r = 0; r < numRows; r++){
            int p1 = r;
            while ( p1 < s.length()){
                res.append(s.charAt(p1));
                if (r != 0 && r != numRows - 1) { // if not first or last one - append additional char
                    int p2 = p1 + (numRows - 1 - r) * 2;
                    if (p2 < s.length()) {
                        res.append(s.charAt(p2));
                    }
                }
                p1 = p1 + numRows + blockLen;
            }
        }
        return res.toString();
    }
}