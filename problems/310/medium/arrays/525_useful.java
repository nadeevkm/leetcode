class Solution {
    public int findMaxLength(int[] nums) {
        int maxWindow = 0;
        Map<Integer, Integer> balanceToInd = new HashMap<>();
        int balance = 0;
        balanceToInd.put(0, -1);
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                balance--;
            } else {
                balance++;
            }
            if (!balanceToInd.containsKey(balance)){
                balanceToInd.put(balance, i);
            } else {
                int l = balanceToInd.get(balance);
                int winSize = i - l; // not like in sliding window i - l + 1, cause left is exluded
                if (winSize > maxWindow){
                    maxWindow = winSize;
                }
            }
        }
        return maxWindow;
    }
}

class Solution {
    public int findMaxLength(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> cntToInd = new HashMap<>();
        cntToInd.put(0, -1);
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += (nums[i] == 1) ? 1 : -1;
            if (cntToInd.containsKey(cnt)) {
                res = Math.max(res, i - cntToInd.get(cnt));
            } else {
                cntToInd.put(cnt, i);
            }
        }
        return res;
    }
}