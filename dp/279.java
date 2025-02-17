class Solution {
    public int numSquares(int n) {
        int[] mem = new int[n + 1];
        int sq = 1;
        for (int i = 1; i < n + 1; i++) {
            if (i == sq * sq) {
                mem[i] = 1;
                sq++;
            } else {
                int min = n;
                for (int j = 1; j < sq; j++) {
                    min = Math.min(min, mem[j * j] + mem[i - j * j]);
                }
                mem[i] = min;
            }
        }
        return mem[n];
    }
}

class SolutionConcise { // can use squares as a boundaries and start from 0
    public int numSquares(int n) {
        int[] mem = new int[n + 1];
        mem[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            int min = n;
            for (int j = 1; j*j <= i; j++) {
                // min = Math.min(min, mem[j * j] + mem[i - j * j]); *** every mem[j*j] is actually '1'
                min = Math.min(min, 1 + mem[i - j * j]);
            }
            mem[i] = min;
        }
        return mem[n];
    }
}
