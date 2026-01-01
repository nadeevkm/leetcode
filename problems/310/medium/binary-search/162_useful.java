class Solution {

    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = r - (r - l) / 2;
            if (diff(m - 1, m, nums) == -1) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        return l;
    }

    private int diff(int m1, int m2, int[] nums){
        long v1 = m1 == -1 ? Long.MIN_VALUE : nums[m1];
        long v2 = m2 == nums.length ? Long.MIN_VALUE : nums[m2];
        return v2 > v1 ? 1 : -1;
    }

}

/*

3      *
2   *
1 *     *


6             *
5          *
4               *
3       *
2   *
1 *  *

*/