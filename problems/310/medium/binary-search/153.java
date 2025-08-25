class Solution {

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r){
            int mid = l + (r-l)/2;
            if (nums[mid] > nums[r]){
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[l];
    }

    public int findMin1(int[] nums) {

        if (nums.length == 1){
            return nums[0];
        }

        int l = 1;
        int r = nums.length - 1;

        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid-1] > nums[mid]){
                return nums[mid];
            } else if (nums[mid] < nums[r]){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return nums[0];
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();

        var nums1 = new int[]{2,3,4,5,6,7,8,9,1};

        var r4 = sol.findMin(nums1);
    }
}
