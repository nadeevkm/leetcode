class Solution {
    public int maxSubArray(int[] nums) {
        int overMax = nums[0];
//        int[] overMaxBounds = new int[]{0,0};  --> can do to find exact msx subarray, not only sum
        int localMax = nums[0];
//        int[] localMaxBounds = new int[]{0,0};
        for (int i = 1; i < nums.length; i++){
            if (localMax + nums[i] >= nums[i]){
                localMax = localMax + nums[i];
            } else { // nums[i] > localMax + nums[i]
                localMax = nums[i];
//                localMaxBounds[0] = i;
            }
//            localMaxBounds[1] = i;

            if (localMax > overMax){
                overMax = localMax;
//                overMaxBounds[0] = localMaxBounds[0];
//                overMaxBounds[1] = localMaxBounds[1];
            }
        }

        return overMax;
    }
}

class SolutionLessCode {
    public int maxSubArray(int[] nums) {
        int overMax = nums[0];
        int localMax = nums[0];
        for (int i = 1; i < nums.length; i++){
            localMax = Math.max(localMax + nums[i], nums[i]);
            overMax = Math.max(localMax, overMax);
        }
        return overMax;
    }
}

class SolutionRepetetion {
    public int maxSubArray(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if (currSum < 0){
                currSum = nums[i];
            } else {
                currSum += nums[i];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}


//[-2,1,-3,4,-1,2,1,-5,4]