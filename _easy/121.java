class Solution { // for every day max profit is price - lowest price so far =>  need to keep track of it and check potential profit for every day
    public int maxProfit(int[] prices) {
        int lowestSoFar = prices[0];
        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            lowestSoFar = Math.min(lowestSoFar, prices[i]);
            profit = Math.max(profit, prices[i] - lowestSoFar);
        }
        return profit;
    }
}
