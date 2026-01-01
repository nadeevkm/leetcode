class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1){
            return 1;
        }

        int[] lastNeg = new int[]{-1,1}; //ind - leng
        int[] lastPos = new int[]{-1,1};

        for (int i = 1; i < nums.length; i++){
            // try to use after longest negative wiggle
            int[] newLastPos = lastPos;
            var lastNegInd = lastNeg[0] == -1 ? i - 1 : lastNeg[0];
            var lastNegLen = lastNeg[1];
            if (nums[i] - nums[lastNegInd] > 0){
                //if (lastNegLen + 1 > lastPos[1]){
                newLastPos = new int[]{i, lastNegLen + 1};
                //}
            }

            // try to use after longest positive wiggle
            int[] newLastNeg = lastNeg;
            var lastPosInd = lastPos[0] == -1 ? i - 1 : lastPos[0];
            var lastPosLen = lastPos[1];
            if (nums[i] - nums[lastPosInd] < 0){
                //if (lastPosLen + 1 > lastNeg[1]){
                newLastNeg = new int[]{i, lastPosLen + 1};
                //}
            }

            lastNeg = newLastNeg;
            lastPos = newLastPos;
        }
        return Math.max(lastNeg[1], lastPos[1]);
    }
}