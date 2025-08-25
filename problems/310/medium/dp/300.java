class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] mem = new int[nums.length];
        int overallMax = 0;
        for (int i = 0; i < nums.length; i++){
            int max = 0;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    max = Math.max(max, mem[j]);
                }
            }
            mem[i] = max + 1;
            overallMax = Math.max(overallMax, mem[i]);
        }
        return overallMax;
    }
}
