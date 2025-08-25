class Solution {
    public int removeDuplicates(int[] nums) {
        int curr = 0;
        int placed = 1;
        //int res = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[curr]){
                if (placed < 2){
                    nums[curr + placed] = nums[i];
                    placed++;
                    //res++;
                }
            } else { // != 
                curr = curr + placed;
                nums[curr] = nums[i];
                placed = 1;
                //res++;
            }
        }
        //return res;
        return curr + placed; // !!! not just curr
    }
}
