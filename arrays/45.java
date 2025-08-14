class Solution {
    public int jump(int[] nums) {
        int steps = 1;
        int frontierInd = nums[0];
        int nextCand = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (frontierInd >= nums.length - 1) {
                return steps;
            }

            nextCand = Math.max(nextCand, i + nums[i]);
            
            if (i == frontierInd) {
                frontierInd = nextCand;
                steps++;
            }
        }
        return 0; // get here only in special case [0]
    }
}
