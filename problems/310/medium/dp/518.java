class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] mem = new int[n + 1][amount + 1];
        for (int r = 0; r < n + 1; r++){
            mem[r][0] = 1;
        }
        for (int r = 1; r < n + 1; r++){
            for (int c = 1; c < amount + 1; c++){
                int coin = coins[r - 1];
                int without = mem[r - 1][c];
                int with = c - coin < 0 ? 0 : mem[r][c - coin];
                mem[r][c] = without + with;
            }
        }
        return mem[n][amount];
    }
}

/*
           0 1 2 3 4 5
 ''      | 1 0 0 0 0 0    
 1       | 1 1 1 1 1 1
 1,2     | 1 1 2 2 3 x
 1,2,5   | 1   x     2
 1,2,5,3 | 1           

*/
 