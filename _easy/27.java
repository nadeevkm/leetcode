class Solution {
    public int removeElement(int[] nums, int val) {
        int l = 0;
        int r = nums.length - 1;
        int cnt = 0;
        while (l <= r) {
            if (nums[l] != val){ // from front - pass all != val, add counter
                cnt++;
                l++;
            } else if (nums[r] == val){ // from end - leave all == val
                r--;
            } else { // nums[l] == val && nums[r] != val, swap such nums
                swap(nums, l, r);
                cnt++;
                l++;
                r--;
            }
        }
        return cnt;
    }

    private void swap(int[] nums, int i1, int i2){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}

class SolutionConcise {
    public int removeElement(int[] nums, int val) {
        int p1 = nums.length - 1;
        int p2 = p1;
        var k = 0;
        while (p1 >= 0) { 
            if (nums[p1] == val) {
                nums[p1] = nums[p2]; 
                nums[p2] = 0;  
                p2--;
            } else {
                k++;
            }
            p1--;
        }
        return k;
    }
}

class SolutionTheMostConcise {
    public int removeElement(int[] nums, int val) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[p] = nums[i];
                p++;
            }
        }
        return p;
    }
}
