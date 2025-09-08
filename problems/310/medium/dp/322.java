class Solution {
    // as if we filling the table
    //
    //  amount -      1 2 3 4 5 ....
    //  coins | c[0]  1
    //          c[1]
    //          ...
    // intersection - is the minimum amount of coins to get target amount
    // (memdp can be reduced to one dimension)
    //
    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount + 1];

        for (int t = 1; t <= amount; t++){
            int minAmount = Integer.MAX_VALUE;
            for (var coin : coins){
                if (t == coin){
                    minAmount = 1;
                    break;
                } else if (t > coin){
                    int prevMin = mem[t - coin];
                    if (prevMin != Integer.MAX_VALUE){
                        minAmount = Math.min(minAmount, 1 + prevMin);
                    }
                }
            }
            mem[t] = minAmount;
        }

        return mem[amount] == Integer.MAX_VALUE ? -1 : mem[amount];
    }
}


/*

 count(amount) = min (count(amount - coin1) + 1, ..., count(amount - coinN) )

 0 1 2 3 4 5 6 .. 11
[0,0,0,0,0,0,0,..,0]

[1,2,5]
*/
class SolutionRepetetion {
    public int coinChange(int[] coins, int amount) {
        // 0,1,.. 11
        var mem = new int[amount + 1];
        // mem[0] = 0, mem[i] = -1
        for (int am = 1; am <= amount; am++) {
            var val = -1;
            for (var c : coins) {
                if (am - c >= 0 && mem[am - c] != -1) {
                    val = val == -1 ? mem[am - c] + 1 : Math.min(val, mem[am - c] + 1);
                }
            }
            mem[am] = val;
        }
        return mem[amount];
    }
}