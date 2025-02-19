class Solution {
    public boolean canJump(int[] nums) {  // O(n)

        int reachDistance = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            reachDistance--;
            reachDistance = Math.max(reachDistance, nums[i]);
            if (reachDistance == 0) {
                return false;
            }
        }

        return true;
    }
}

class SolutionDP {
    public boolean canJump(int[] nums) {  // DP
        boolean[] mem = new boolean[nums.length];
        mem[0] = true;

        for (int i = 1; i < nums.length; i++){
            for (int j = i - 1; j >= 0; j--){
                if (mem[j] && j + nums[j] >= i){
                    mem[i] = true;
                    break;
                }
            }
        }

        return mem[nums.length - 1];
    }
}
