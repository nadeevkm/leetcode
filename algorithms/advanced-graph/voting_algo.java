// see 169 Leetcode

class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 0;
        for (int n : nums){
            if (n == major){
                count++;
            } else {
                count--;
                if (count <= 0){
                    major = n;
                    count = 1;
                }
            }
        }
        return major;
    }
}