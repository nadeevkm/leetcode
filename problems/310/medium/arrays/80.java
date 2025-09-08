class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        int curr = nums[slow];
        int cnt = 1;

        while (fast != nums.length) {
            if (nums[fast] != curr) {
                cnt = Math.min(2, cnt);
                while (cnt > 0) {
                    nums[slow] = curr;
                    slow++;
                    cnt--;
                }
                curr = nums[fast];
                cnt = 1;
            } else { // nums[fast] == curr
                cnt++;
            }
            fast++;
        }

        cnt = Math.min(2, cnt);
        while (cnt > 0) {
            nums[slow] = curr;
            slow++;
            cnt--;
        }

        return slow;
    }
}

class SolutionMoreConcise {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        int curr = nums[slow];
        int cnt = 1;

        while (true) {
            if (fast == nums.length || nums[fast] != curr) {
                cnt = Math.min(2, cnt);
                while (cnt > 0) {
                    nums[slow] = curr;
                    slow++;
                    cnt--;
                }
                if (fast == nums.length){
                    break;
                }
                curr = nums[fast];
                cnt = 1;
            } else { // nums[fast] == curr
                cnt++;
            }
            fast++;
        }

        return slow;
    }
}

class SolutionMostConcise {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int fast = 1; // Pointer to iterate through the array
        int slow = 1; // Pointer to track position for valid elements
        int count = 1; // Count of occurrences of the current element

        while (fast < nums.length) {
            if (nums[fast] == nums[fast - 1]) {
                count++;
                if (count > 2) {
                    fast++;
                    continue;
                }
            } else {
                count = 1;
            }
            nums[slow] = nums[fast];
            slow++;
            fast++;
        }

        return slow;
    }
}

class SolutionRepetitMostOptimal {
    public int removeDuplicates(int[] nums) {
        int curr = 0;
        int placed = 1;
        //int res = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[curr]){
                if (placed < 2){
                    nums[curr + placed] = nums[i];
                    placed++;
                    //res++;
                }
            } else { // != 
                curr = curr + placed;
                nums[curr] = nums[i];
                placed = 1;
                //res++;
            }
        }
        //return res;
        return curr + placed; // !!! not just curr
    }
}
