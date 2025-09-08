class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] mem = new int[l1 + 1][l2 + 1];

        for (int r = 1; r < l1 + 1; r++) {
            for (int c = 1; c < l2 + 1; c++) {

                var cand = mem[r - 1][c - 1] + (text2.charAt(c - 1) == text1.charAt(r- 1) ? 1 : 0);
                mem[r][c] = Math.max(cand, Math.max(mem[r][c-1], mem[r-1][c]));
            }
        }

        return mem[l1][l2];
    }
}

class SolutionOptimized { // actually if we are checking adding characters which are equal, it alwasy will be the best possible result, so no need to check Math.max in that case
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] mem = new int[l1 + 1][l2 + 1];

        for (int r = 1; r < l1 + 1; r++) {
            for (int c = 1; c < l2 + 1; c++) {
                mem[r][c] = (text2.charAt(c - 1) == text1.charAt(r - 1))
                        ? 1 + mem[r-1][c-1]
                        : Math.max(mem[r][c-1], mem[r-1][c]);
            }
        }

        return mem[l1][l2];
    }
}