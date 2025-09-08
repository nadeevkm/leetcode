import java.util.Deque;
import java.util.LinkedList;


class SolutionLinear {  // Greedy?.. idea not to calculate result jumps, but check whether jumps from 1 to... are solution

    //    lb   r
    //        nr
    //    i
    //   [2,3,1,1,4]
    public int jump(int[] nums) {
        int jumps = 1;
        int leftBoundary = -1;
        int reachLevel = 0;

        while (reachLevel < nums.length - 1){
            int newReach = 0;
            for (int i = leftBoundary + 1; i <= reachLevel; i++){
                newReach = Math.max(newReach, i + nums[i]);
                if (newReach >= nums.length - 1){
                    return jumps;
                }
            }
            leftBoundary = reachLevel;
            reachLevel = newReach;
            jumps++;
        }

        return 0;
    }
}

class SolutionSimplified {
    public int jump(int[] nums) {
        int i = 0;
        int currStepReach = 1;
        int nextStepReach = -1;
        int cnt = 0;
        while(i != nums.length - 1){
            nextStepReach--;
            nextStepReach = Math.max(nextStepReach, nums[i]);
            currStepReach--;
            if (currStepReach == 0){
                currStepReach = nextStepReach;
                cnt++;
                nextStepReach = -1;
            }
            i++;
        }
        return cnt;
    }
}

class SolutionDpLike { // more like DP inspired

    //    i
    //  0 1 1 2 2
    // [2,3,1,1,4]
    public int jump(int[] nums) {

        int[] mem = new int[nums.length];
        mem[0] = 0;

        for (int i = 0; i < nums.length - 1; i++){
            for (int j = 1; j <= nums[i] && (i + j) < nums.length; j++){
                if (mem[i + j] == 0){
                    mem[i + j] = mem[i] + 1;
                    if (i + j == nums.length - 1){
                        return mem[i + j];
                    }
                }
            }
        }

        return 0;
    }
}


class SolutionDp { // DP

    public int jump(int[] nums) {

        int [] mem = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            mem[i] = Integer.MAX_VALUE;
        }
        mem[0] = 0;

        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (j + nums[j] >= i) {
                    mem[i] = Math.min(mem[i], mem[j] + 1);
                }
            }
        }
        return mem[nums.length - 1];
    }
}

class SolutionBFS {
    public int jump(int[] nums) {  // BFS solution, think of it as graph problem, O(v + e) -> upper bounds O(n + n^2) -> O(n^2)

        int[] marked = new int[nums.length];
        Deque<Integer> q = new LinkedList<>();

        q.add(0);
        marked[0] = 1;

        int level = 0;
        while (!q.isEmpty()){

            int size = q.size();
            while (size-- > 0){
                int pos = q.poll();
                if (pos == nums.length - 1){
                    return level;
                }

                for (int j = 1; j <= nums[pos] && (pos + j) < nums.length; j++){
                    if (marked[pos + j] != 1){
                        q.add(pos + j);
                        marked[pos + j] = 1;
                    }
                }
            }
            level++;
        }

        return -1;
    }
}