class Solution { // floyd cycle detection algo
    public int findDuplicate(int[] nums) {
        int slowInd = 0;
        int fastInd = 0;  // !! important to set s and f to one place, otherwise they not meet in the second part
        while (true){ // find cycle
            slowInd = nums[slowInd];
            fastInd = nums[nums[fastInd]];
            if (slowInd == fastInd){
                break;
            }
        }
        slowInd = 0;
        while(slowInd != fastInd){ // find cycle start
            slowInd = nums[slowInd];
            fastInd = nums[fastInd];
        }
        return slowInd;
    }
}

class SolutionNotCorrect { // if we can modify the array, this solution will be valid
    //   0  1  2  3  4  5
    // [ 5, 4, 3, 2, 1, 5]
    //                 -1
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) { // in the original array they should all be >= 1, = n + 1
                continue;
            }
            int prev = i;
            int curr = nums[i] - 1;
            while (true) {
                if (nums[curr] >= 1) { // we've never been here
                    int next = nums[curr] - 1;
                    nums[curr] = -1 * prev;
                    prev = curr;
                    curr = next;
                } else if (nums[curr] != -1 * prev) { // we've already been here from another index
                    return curr + 1;
                } else { //  we've been here, but from same previous index => we've made a full circle
                    break;
                }
            }
        }
        return -1;
    }
}

class Check {
    public static void main(String[] args) {
//        var r1 = new Solution().findDuplicate(new int[]{5,4,3,2,1,5});
//        var r2 = new Solution().findDuplicate(new int[]{2, 3, 1, 3});
        var r3 = new Solution().findDuplicate(new int[]{1, 3, 4, 2, 2});
        var rr = 0;
    }
}
