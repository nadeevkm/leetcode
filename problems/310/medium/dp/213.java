class Solution {
    public int rob(int[] nums) {
        if (nums.length < 3){
            return nums.length == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }
        return Math.max(rob(nums,0,nums.length - 2), rob(nums,1,nums.length - 1));
    }

    private int rob(int[] nums, int st, int end){
        int prevStale = 0;
        int prevRob = nums[st];
        for (int i = st + 1; i <= end; i++){
            int stale = Math.max(prevRob, prevStale);
            int rob = prevStale + nums[i];
            prevStale = stale;
            prevRob = rob;
        }
        return Math.max(prevStale, prevRob);
    }
}