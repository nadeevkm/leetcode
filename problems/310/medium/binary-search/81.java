class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 1){
            return nums[0] == target;
        }
        int l = 0;
        int r = nums.length - 1;
        if (nums[l] < nums[r]) {
            return findBinary(nums, 0, target); // shift 0
        } else if (nums[l] > nums[r]) {
            int dropInd = findDropInd(l, r, nums);
            return findBinary(nums, dropInd + 1, target);
        }
        while (l < r && nums[l] == nums[r]){
            r--; // can also trim from left
        }
        if (r == 0){
            return nums[l] == target; // for cases nums = [1,1], t = 1/2..
        }
        int dropInd = findDropInd(l, r, nums);
        return findBinary(nums, dropInd + 1, target);
    }

    public boolean findBinary(int[] nums, int shift, int target) {
        int l = shift;
        int r = shift + nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m % nums.length] == target) {
                return true;
            } else if (nums[m % nums.length] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public int findDropInd(int l, int r, int[] nums) {
        // i.e. find max in shifted array
        int cand = r;
        r = r - 1;
        while (l <= r){
            int m = l + (r-l)/2;
            if (nums[m] > nums[m+1]){
                return m;
            } else if (nums[m] >= nums[l]){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return cand;
    }
}

class SolutionRepetit {
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target || nums[l] == target) {
                return true;
            } else if (nums[m] < nums[l]) {
                if (target > nums[l]) {
                    r = m - 1;
                } else { // target < nums[l]
                    if (nums[m] < target) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            } else if (nums[m] > nums[l]) {
                if (target < nums[l]) {
                    l = m + 1;
                } else { // target > nums[l]
                    if (nums[m] < target) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            } else { // nums[m] == nums[l] && nums[l] != target
                l++;
            }
        }
        return false;
    }
}




class Check{
    public static void main(String[] args) {
        var res = new Solution().search(new int[]{2,2,1,2}, 1);
    }
}