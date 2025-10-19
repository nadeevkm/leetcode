class Solution {
    public int rob(int[] nums) {

        int maxPrevTaken = 0;
        int maxPrevNotTaken = 0;
        for (int i = 0; i < nums.length; i++){
            int currTaken = nums[i] + maxPrevNotTaken;
            int currNotTaken = Math.max(maxPrevNotTaken, maxPrevTaken);

            maxPrevTaken = currTaken;
            maxPrevNotTaken = currNotTaken;
        }

        return Math.max(maxPrevTaken, maxPrevNotTaken);
    }
}