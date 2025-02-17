class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int lp = 0;
        int rp = numbers.length - 1;
        while (lp < rp){
            if (numbers[lp] + numbers[rp] < target) {
                lp++;
            } else if (numbers[lp] + numbers[rp] > target){
                rp--;
            } else {
                return new int[]{lp + 1, rp + 1};
            }
        }
        return new int[0];
    }
}
