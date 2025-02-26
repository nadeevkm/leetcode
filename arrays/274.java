class Solution {
    public int hIndex(int[] citations) {
        int[] hArr = new int[citations.length];
        for (int i = 0; i < citations.length; i++){
            if (citations[i] >= hArr.length){
                hArr[hArr.length -1] = hArr[hArr.length -1] + 1; //0,0,0
            } else if (citations[i] > 0) {
                hArr[citations[i] - 1] = hArr[citations[i] - 1] + 1; // 2,0,1
            }
        }
        int hInd = 0;
        for(int j = hArr.length - 1; j >= 0; j--){
            hInd = hInd + hArr[j];
            if (hInd >= j + 1) {
                return j + 1;
            }
        }
        return 0;
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var nums = new int[]{3,0,6,1,5};

        var pp = sol.hIndex(nums);
    }
}
