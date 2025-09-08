class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;

//        int i = n;
//        while (i >= 1 && nums[i - 1] >= nums[i]) {
//            i--;
//        }
//        i--;
        // better do it this way
        int i = n - 1;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(j, i, nums);
        }
        reverse(i + 1, nums);
    }

    private void swap(int i1, int i2, int[] nums) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    private void reverse(int st, int[] nums) {
        int end = nums.length - 1;
        while (st < end) {
            swap(st, end, nums);
            st++;
            end--;
        }
    }
}

class SolutionRepetit {
    public void nextPermutation(int[] nums) {
        int p = nums.length - 1;
        while (p > 0 && nums[p - 1] >= nums[p]) {
            p--;
        }
        if (p > 0) {
            int toSwap = nums.length - 1;
            while (nums[toSwap] <= nums[p - 1]){
                toSwap--;
            }
            swap(nums, p - 1, toSwap);
        }
        reverse(nums, p);
    }

    public void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

//    public void reverse(int[] nums, int start) {
//        int p = nums.length - 1;
//        for (int i = start; i <= start + ((nums.length - 1 - start) / 2); i++) {
//            swap(nums, i, p);
//            p--;
//        }
//    }

    public void reverse(int[] nums, int start) { // easier reverse
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}