class Solution {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(0, nums, new LinkedList<>());
        return res;
    }

    private void backtrack(int ind, int[] nums, List<Integer> subset){
        res.add(new LinkedList<>(subset));
        for (int i = ind; i < nums.length; i++){
            if (i > ind && nums[i] == nums[i - 1]){
                continue;
            }
            subset.add(nums[i]);
            backtrack(i + 1, nums, subset);
            subset.remove(subset.size() - 1);
        }
    }
}