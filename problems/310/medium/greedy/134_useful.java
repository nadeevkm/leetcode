class Solution {

     //             ps
     //       0 1 2 3 4
    // gas  = 1 2 3 4 5
    // cost = 3 4 5 1 2
    // b = 1+5+2+4+3
    // c = 3+2+4+1+5
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 1){
            return gas[0] >= cost[0] ? 0 : -1;
        }
        int stCand = 0;
        int pathEnd = 1;
        int curBal = gas[stCand];
        int pathCost = cost[pathEnd - 1];

        while (stCand != pathEnd){
            if (curBal >= pathCost){
                pathEnd++;
                curBal = curBal + gas[pathEnd-1];
                pathCost = pathCost + cost[pathEnd-1];
                if (pathEnd == n){
                    pathEnd = 0;
                }
            } else {
                stCand--;
                if (stCand == -1){
                    stCand = n - 1;
                }
                curBal = curBal + gas[stCand];
                pathCost = pathCost + cost[stCand];
            }
        }

        return curBal >= pathCost ? stCand : -1;
    }


    public int canCompleteCircuit2(int[] gas, int[] cost) { // can simplify by calculating difference (i.e. tank instead of gas/cost) on the fly
        int n = gas.length;
        if (n == 1){
            return gas[0] >= cost[0] ? 0 : -1;
        }
        int stCand = 0;
        int pathEnd = 1;
        int tank = gas[stCand] - cost[pathEnd - 1];

        while (stCand != pathEnd){
            if (tank >= 0){
                pathEnd++;
                tank = tank + gas[pathEnd - 1] - cost[pathEnd - 1];
                if (pathEnd == n){
                    pathEnd = 0;
                }
            } else {
                stCand--;
                if (stCand == -1){
                    stCand = n - 1;
                }
                tank = tank + gas[stCand] - cost[stCand];
            }
        }

        return tank >= 0 ? stCand : -1;
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) { // can simplify further by choosing starting point differently
        int n = gas.length;
        int stCand = n - 1;
        int pathEnd = 0;
        int tank = gas[stCand] - cost[stCand];

        while (stCand != pathEnd){
            if (tank >= 0){ // in order to pass tests, tank > 0 should be here instead
                pathEnd++;
                tank = tank + gas[pathEnd - 1] - cost[pathEnd - 1];
            } else {
                stCand--;
                tank = tank + gas[stCand] - cost[stCand];
            }
        }

        return tank >= 0 ? stCand : -1;
    }

    public int canCompleteCircuit4(int[] gas, int[] cost) { // slightly different approach
        int n = gas.length;
        int stPoint = 0;
        int tankToSp = 0;
        int tankFromSp = gas[0] - cost[0];

        for (int target = 1; target < n; target++){
            if (tankFromSp >= 0){
                tankFromSp = tankFromSp + gas[target] - cost[target];
            } else {
                tankToSp = tankToSp + tankFromSp;
                tankFromSp = gas[target] - cost[target];
            }
        }

        return (tankToSp + tankFromSp) >= 0 ? stPoint : -1;
    }
}

class Check{
    public static void main(String[] args) {
        int res = new Solution().canCompleteCircuit(
                new int[]{5, 1, 2, 3, 4},
                new int[]{4, 4, 1, 5, 1}
                );
        int pp = 2;
    }
}

class SolutionRepetit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int st = 0;
        int balance = 0;
        int curr = 0;
        while (curr == 0 || Math.floorMod(st, n) != (curr % n)) {
            if (balance < 0) { // then try different starting point
                balance += gas[Math.floorMod(st - 1, n)];
                balance -= cost[Math.floorMod(st - 1, n)];
                st--;
            } else {
                balance += gas[curr % n];
                balance -= cost[curr % n];
                curr++;
            }
        }
        return balance >= 0 ? Math.floorMod(st, n) : -1;
    }
}
