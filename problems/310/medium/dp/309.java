class Solution {
    public int maxProfit(int[] prices) {
        int hold = -1 * prices[0];
        int emptyReady = 0;
        int emptyWaiting = 0;
        for (int i = 1; i < prices.length; i++) {
            int newWaiting = Math.max(emptyWaiting, hold + prices[i]);
            int newReady = Math.max(emptyReady, emptyWaiting);
            int newHold = Math.max(hold, emptyReady - prices[i]);

            hold = newHold;
            emptyReady = newReady;
            emptyWaiting = newWaiting;
        }
        return Math.max(emptyReady, emptyWaiting);
    }
}

class Check{
    public static void main(String[] args) {
        var res = new Solution().maxProfit(new int[]{1,2,3,0,2});
        var p = 0;
    }
}
