class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){
            return false;
        }

        boolean[][] mem = new boolean[s1.length()+1][s2.length()+1];
        mem[0][0] = true;
        for (int c = 1; c < s2.length() + 1; c++){
            if (mem[0][c-1] && s3.charAt(c - 1) == s2.charAt(c-1)){
                mem[0][c] = true;
            } else {
                break;
            }
        }

        for (int r = 1; r < s1.length() + 1; r++){
            if (mem[r - 1][0] && s3.charAt(r - 1) == s1.charAt(r-1)){
                mem[r][0] = true;
            } else {
                break;
            }
        }

        for (int r = 1; r < s1.length() + 1; r++){
            for (int c = 1; c < s2.length() + 1; c++){
                if ((mem[r-1][c] && s1.charAt(r-1) == s3.charAt(r+c-1)) ||
                (mem[r][c-1] && s2.charAt(c-1) == s3.charAt(r+c-1))){
                    mem[r][c] = true;
                }
            }
        }

        return mem[s1.length()][s2.length()];
    }
}

class SolutionRepetit {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()){
            return false;
        }
        boolean[][] mem = new boolean[s1.length() + 1][s2.length() + 1];
        mem[0][0] = true;
        for (int c = 1; c < s2.length() + 1; c++) {
            if (mem[0][c - 1]) {
                mem[0][c] = s3.charAt(c - 1) == s2.charAt(c - 1);
            }
        }
        for (int r = 1; r < s1.length() + 1; r++) {
            if (mem[r - 1][0]) {
                mem[r][0] = s3.charAt(r - 1) == s1.charAt(r - 1);
            }
        }
        for (int r = 1; r < s1.length() + 1; r++) {
            for (int c = 1; c < s2.length() + 1; c++) {
                var pos1 = mem[r - 1][c] && s3.charAt(r + c - 1) == s1.charAt(r - 1);
                var pos2 = mem[r][c - 1] && s3.charAt(r + c - 1) == s2.charAt(c - 1);
                mem[r][c] = pos1 || pos2;
            }
        }
        return mem[s1.length()][s2.length()];
    }
}

/*

           ""  d  db  dbb  dbbc dbbca
           -------------------------
    ""    | T  F  F
    a     | T
    aa.   | T
    aab.  |
    aabc. |                      ?
    aabcc |                ?     X


    "aadbbcbcac"

*/