import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int closSum = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length - 1; i++){
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < nums.length){
                int sum = nums[l] + nums[i] + nums[r];
                int diff = Math.abs(target - sum);
                if (diff < minDiff){
                    minDiff = diff;
                    closSum = sum;
                    if (diff == 0){
                        return sum;
                    }
                }
                if (sum < target){
                    r++;
                } else {
                    l--;
                }
            }
        }
        return closSum;
    }
}


class SolutionRepetetion {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int latestDiff = Integer.MAX_VALUE;
        int closest = Integer.MAX_VALUE;
        for (int p = 0; p <= n - 3; p++) {
            int l = p + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[p] + nums[l] + nums[r];
                int diff = target >= sum ? target - sum : sum - target;
                if (diff < latestDiff) {
                    latestDiff = diff;
                    closest = sum;
                }
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    return target;
                }
            }
        }
        return closest;
    }
}