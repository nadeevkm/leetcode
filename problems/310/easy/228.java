class Solution {
    public List<String> summaryRanges(int[] nums) {      
        if (nums.length == 1) {
            return Arrays.asList(String.valueOf(nums[0]));
        }
        var res = new LinkedList<String>();
        int p = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (i != nums.length && nums[i] == nums[p] + i - p) {
                continue;
            }
            if (i - 1 == p) {
                res.add(String.valueOf(nums[p]));
            } else {
                res.add(nums[p] + "->" + nums[i - 1]);
            }
            p = i;
        }
        return res;
    }
}
