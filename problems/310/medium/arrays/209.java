class Solution {
    public int minSubArrayLen1(int target, int[] nums) {  // quicker, but more code - move whole window, when find it
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int lp = 0;
        int rp = 0;
        int sum = 0;
        while (rp < nums.length && sum < target) {
            sum = sum + nums[rp];
            if (sum >= target) {
                break;
            }
            rp++;
        }
        if (sum < target) {
            return 0;
        }
        while (lp < rp) {
            int potSum = sum - nums[lp];
            if (potSum >= target) {
                lp++;
                sum = potSum;
            } else {
                break;
            }
        }
        while (rp < nums.length - 1) {
            sum = sum - nums[lp];
            lp++;
            rp++;
            sum = sum + nums[rp];
            while (lp < rp) {
                int potSum = sum - nums[lp];
                if (potSum >= target) {
                    lp++;
                    sum = potSum;
                } else {
                    break;
                }
            }
        }
        return rp - lp + 1;
    }


    public int minSubArrayLen(int target, int[] nums) { // slightly slower ( still O(n) ), but less code - on every iteration move only one pointer of a window
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int lp = 0;
        int rp = 0;
        int sum = nums[rp];
        int minWinSize = nums.length + 1;
        //  0 1
        // [1,2] - targ 2
        // lp - 1, rp - 1, sum - 2, minSize - 1
        while (rp < nums.length) {
            if (rp - lp + 1 > minWinSize || sum >= target) {
                if (sum >= target) {
                    minWinSize = Math.min(minWinSize, rp - lp + 1);
                    if (minWinSize == 1) {
                        return 1;
                    }
                }
                sum = sum - nums[lp];
                lp++;
            } else { // sum < target
                rp++;
                if (rp < nums.length){
                    sum = sum + nums[rp];
                }
            }
        }
        return minWinSize == nums.length + 1 ? 0 : minWinSize;
    }

    public int minSubArrayLenOpt(int target, int[] nums) { // clean code
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int lp = 0;
        int rp = 0;
        int sum = nums[rp];
        int minWinSize = nums.length + 1;
        while (rp < nums.length) {
                sum = sum + nums[rp];
                rp++;
                while (sum >= target) {
                    minWinSize = Math.min(minWinSize, rp - lp);
                    sum = sum - nums[lp];
                    lp++;
                }
            }
        return minWinSize == nums.length + 1 ? 0 : minWinSize;
    }
};


class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var nums = new int[]{1,1,1,1,1,1,1,1};
        var target = 11;
        var r1 = sol.minSubArrayLen(target, nums);
    }
}



class SolutionRepetetion{
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;

        int[] presums = new int[nums.length];
        presums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            presums[i] = presums[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int targetSum = i == 0 ? target : target + presums[i - 1];
            int l = i;
            int r = nums.length;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (presums[m] >= targetSum) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            if (l < nums.length) { // i.e. target sum was found
                minLen = Math.min(minLen, l - i + 1);
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}

class SolutionConcise {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        for (int r = 0; r < nums.length; r++){
            sum += nums[r];

            if (r - l + 1 >= minLen){
                sum -= nums[l];
                l++;
            }

            while(sum >= target){
                minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}

class SolutionTop {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        for (int r = 0; r < nums.length; r++){
            sum += nums[r];

            while(sum >= target){
                minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}