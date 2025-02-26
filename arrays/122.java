import java.util.LinkedList;

class Solution {


    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int maxBoughtStateProfit = -prices[0];
        int maxSoldStateProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int newMaxBoughtStateProfit = Math.max(maxBoughtStateProfit, maxSoldStateProfit - prices[i]);
            int newMaxSoldStateProfit = Math.max(maxSoldStateProfit, maxBoughtStateProfit + prices[i]);
            maxBoughtStateProfit = newMaxBoughtStateProfit;
            maxSoldStateProfit = newMaxSoldStateProfit;
        }
        return maxSoldStateProfit;
    }

    public int maxProfit2(int[] prices) {
        var maxProfit = 0;
        Integer openPosition = null;
        for (int i = 0; i < prices.length; i++){
            if (openPosition == null){
                if (i + 1 < prices.length && prices[i+1] > prices[i]){
                    openPosition = prices[i];
                }
            } else { // openPos != null
                if (i + 1 >= prices.length || prices[i+1] < prices[i]) {
                    maxProfit = maxProfit + (prices[i] - openPosition);
                    openPosition = null;
                }
            }
        }
        return maxProfit;
    }

    public int maxProfitRecur(int[] prices) {
        var maxProfit = 0;
        var initState = new State(null, 0, 0);
        var stateQueue = new LinkedList<State>();
        stateQueue.add(initState);
        while(!stateQueue.isEmpty()){
            var currState = stateQueue.pop();
            if(currState.dayIndex >= prices.length)
                continue;
            var nextStatePath1 = new State(currState.order, currState.profit, currState.dayIndex + 1);
            stateQueue.add(nextStatePath1);
            if(currState.order != null) {
                var todayProfit = prices[currState.dayIndex];
                maxProfit = Math.max(maxProfit, currState.profit + todayProfit);
                var nextStatePath2 = new State(null, currState.profit + todayProfit, currState.dayIndex + 1);
                stateQueue.add(nextStatePath2);
            } else { // currState.order == null
                var todayProfit = -prices[currState.dayIndex];
                var nextStatePath2= new State(prices[currState.dayIndex], currState.profit + todayProfit, currState.dayIndex + 1);
                stateQueue.add(nextStatePath2);
            }
        }
        return maxProfit;
    }

    public int maxProfitOptStates(int[] prices) {
        var maxProfit = 0;
        var initState1 = new State(null, 0, 1);
        var initState2 = new State(prices[0], -prices[0], 1);
        var stateQueue = new LinkedList<State>();
        stateQueue.add(initState1);
        stateQueue.add(initState2);
        while(!stateQueue.isEmpty()){
            var currState = stateQueue.pop();
            if(currState.dayIndex >= prices.length)
                continue;
            if(currState.order != null) {
                if (prices[currState.dayIndex] < prices[currState.dayIndex - 1]){
                    continue;
                } else {
                    if (currState.dayIndex + 1 >= prices.length || prices[currState.dayIndex + 1] < prices[currState.dayIndex]) {
                        var todayProfit = prices[currState.dayIndex];
                        maxProfit = Math.max(maxProfit, currState.profit + todayProfit);
                        var nextStatePath2 = new State(null, currState.profit + todayProfit, currState.dayIndex + 1);
                        stateQueue.add(nextStatePath2);
                    }
                    else {
                        var nextStatePath1 = new State(currState.order, currState.profit, currState.dayIndex + 1);
                        stateQueue.add(nextStatePath1);
                    }
                }
            } else {
                if (prices[currState.dayIndex] > prices[currState.dayIndex - 1]) {
                    continue;
                } else {
                    if (currState.dayIndex + 1 >= prices.length || prices[currState.dayIndex + 1] < prices[currState.dayIndex]) {
                        var nextStatePath1 = new State(currState.order, currState.profit, currState.dayIndex + 1);
                        stateQueue.add(nextStatePath1);
                    } else {
                        var todayProfit = -prices[currState.dayIndex];
                        var nextStatePath2 = new State(prices[currState.dayIndex], currState.profit + todayProfit, currState.dayIndex + 1);
                        stateQueue.add(nextStatePath2);
                    }
                }
            }
        }
        return maxProfit;
    }

    class State{
        Integer order;
        int profit;
        int dayIndex;

        public State(Integer order, int profit, int dayIndex) {
            this.order = order;
            this.profit = profit;
            this.dayIndex = dayIndex;
        }
    }

    private int maxProfitRecur(int[] prices, int position, Integer stock, int profit){
        if (position >= prices.length){
            return profit;
        }
        int opt1, opt2;
        // do nothing
        opt1 = maxProfitRecur(prices, position+1, stock, profit);
        if (stock == null) {
            // buy
            opt2 = maxProfitRecur(prices, position+1, prices[position], profit - prices[position]);
        } else {  // stock != null
            // sell
            if (prices[position] > stock ) {
                opt2 = maxProfitRecur(prices, position + 1, null, profit + prices[position]);
            } else {
                opt2 = opt1;
            }
        }
        return Math.max(opt1, opt2);
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var nums = new int[]{1,2,3,4,5};

        var pp = sol.maxProfit(nums);
    }
}
