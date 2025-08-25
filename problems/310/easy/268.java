class SolutionSwap { // use number indexes for checking missing number
    public int missingNumber(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++){
            while (nums[i] < n && nums[i] != i){
                swap(nums, i, nums[i]);
            }
        }

        for (int i = 0; i < n; i++){
            if (i != nums[i]){
                return i;
            }
        }

        return n;
    }

    public void swap(int[] nums, int i1, int i2){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
