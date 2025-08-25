import java.util.Arrays;

class Solution {
    public long largestEvenSum(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        long sum = 0;
        for (int i = n - k; i < n; i++){
            sum += nums[i];
        }
        if (sum % 2 == 0){
            return sum;
        }
        Integer minEven = null;
        Integer minOdd = null;
        for (int i = n - 1; i >= n - k; i--){
            if (nums[i] % 2 == 0){
                minEven = nums[i];
            } else {
                minOdd = nums[i];
            }
        }
        Integer maxEven = null;
        Integer maxOdd = null;
        for (int i = 0; i < n - k; i++){
            if (nums[i] % 2 == 0){
                maxEven = nums[i];
            } else {
                maxOdd = nums[i];
            }
        }
        long maxsum = -1;
        if (minEven != null && maxOdd != null) {
            maxsum = Math.max(maxsum, sum - minEven + maxOdd);
        }
        if (minOdd != null && maxEven != null){
            maxsum = Math.max(maxsum, sum - minOdd + maxEven);
        }
        return maxsum;
    }
}