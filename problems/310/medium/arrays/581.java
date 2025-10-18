class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2){
            return 0;
        }

        int l = 0;
        boolean foundNonSorted = false;
        for (int i = 1; i < nums.length; i++){
            if(!foundNonSorted && nums[i] >= nums[l]){
                l++;
            } else {
                while(l >= 0 && nums[i] < nums[l]){
                    l--;
                }
                foundNonSorted = true;
            }
        }

        int r = nums.length - 1;
        foundNonSorted = false;
        for (int i = r - 1; i >= 0; i--){
            if (!foundNonSorted && nums[i] <= nums[l]){
                r--;
            } else {
                while(r < nums.length && nums[i] > nums[l]){
                    r++;
                }
                foundNonSorted = true;
            }
        }

        int diff = r - l + 1;
        return diff > 0 ? diff : 0;
    }
}

class SolutionRepetit {

    public int findUnsortedSubarray(int[] nums) {
        int lp = 0;
        int rp = nums.length - 1;

        while ((lp + 1 <= nums.length - 1) && nums[lp + 1] >= nums[lp]){
            lp++;
        }
        for (int i = lp; i <= nums.length - 1; i++){
            while (lp >= 0 && nums[i] < nums[lp]){
                lp--;
            }
        }

        while ((rp - 1 >= 0) && nums[rp - 1] <= nums[rp]){
            rp--;
        }
        for (int i = rp; i >= 0; i--){
            while(rp <= nums.length - 1 && nums[i] > nums[rp]){
                rp++;
            }
        }

        return lp >= rp ? 0 : (rp - 1) - (lp + 1) + 1;
    }
}