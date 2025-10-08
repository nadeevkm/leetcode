class Solution {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // find lowest
        while (right - left > 1){
            int mid = (left + right)/2;
            if (nums[mid] < nums[right]){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // left == lowest
        if (target < nums[left]){
            return binarySearch(nums, target, 0, left);
        } else if (target > nums[left]){
            return binarySearch(nums, target, left, nums.length);
        } else {
            return left;
        }
    }

    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        boolean rotation = nums[right] <= nums[left];

        if (!rotation) {
            return binarySearch(nums, target, left, right);
        } else {
            // find drop
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (nums[mid] > nums[left]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            if (target >= nums[0]) {
                // peak == left
                return binarySearch(nums, target, 0, left);
            } else {
                // bottom = right
                return binarySearch(nums, target, right, nums.length - 1);
            }
        }
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        int left = start;
        int right = end;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();

        var nums1 = new int[]{5,1,3};

        var r4 = sol.search(nums1, 1);
    }
}

class SolutionRepetit {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = l + (r - l)/2;
            if (nums[m] == target){
                return m;
            }
            if (nums[m] < nums[l]){ // we are in the right half
                // how we can discard the bigger half? 
                if (nums[m] < target && target < nums[l]){ // only then we can discard the bigger half
                    l = m + 1;
                } else { // nums[m] > target || target >= nums[l]
                    r = m - 1;
                }
            } else { // nums[m] >= nums[l], int the left half
                if (nums[m] > target && target >= nums[l]){ // we can discard the bigger half
                    r = m - 1;
                } else { // nums[m] < target || target < nums[l]
                    l = m + 1;
                }
            }
        }
        return -1;
    }
}