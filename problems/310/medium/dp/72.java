class Solution {
    public int minDistance(String word1, String word2) {
        int[][] mem = new int[word2.length() + 1][word1.length() + 1];
        for (int r = 0; r < mem.length; r++) {
            mem[r][0] = r;
        }
        for (int c = 0; c < mem[0].length; c++) {
            mem[0][c] = c;
        }
        for (int r = 1; r < mem.length; r++) {
            for (int c = 1; c < mem[0].length; c++) {
                if (word2.charAt(r - 1) == word1.charAt(c - 1)) {
                    mem[r][c] = mem[r - 1][c - 1];
                } else {
                    mem[r][c] = Math.min(mem[r - 1][c - 1], Math.min(mem[r - 1][c], mem[r][c - 1])) + 1;
                }
            }
        }
        return mem[mem.length - 1][mem[0].length - 1];
    }
}

/*.

            h
          h o . .
     "" h o r s e
""   0. 1 2 3 4 5
r.   1  1   2
ro.  2      3
ros  3.       3
rose 4


hors e
  ro s

f(hors -> ros) = min( f(horse -> ro) + f (hors -> ros) + f(hors -> ro) ) + 1

hors s
  or s




*/