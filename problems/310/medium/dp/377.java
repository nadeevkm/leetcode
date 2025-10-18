class SolutionTopToBottom {

    Integer[] mem;

    public int combinationSum4(int[] nums, int target) {
        mem = new Integer[target + 1];
        mem[0] = 1;
        combinationSum(nums, target);
        return mem[target];
    }

    private int combinationSum(int[] nums, int target){
        if (target < 0){
            return 0;
        }
        if (mem[target] != null){
            return mem[target];
        }

        int count = 0;
        for (int num : nums){
            count += combinationSum(nums, target - num);
        }

        mem[target] = count;
        return count;
    }
}


class SolutionBottomToTop {
    public int combinationSum4(int[] nums, int target) {
        int[] mem = new int[target + 1];
        mem[0] = 1;
        for (int s = 1; s <= target; s++){
            for (int n : nums){
                mem[s] += (s - n) >= 0 ? mem[s-n] : 0;
            }
        }
        return mem[target];
    }
}