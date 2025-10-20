import java.util.Random;

class Solution {

    int[] orig;
    int[] shuffled;
    Random rand;

    public Solution(int[] nums) {
        orig = nums;
        shuffled = new int[nums.length];
        System.arraycopy(orig, 0, shuffled, 0, orig.length);
        rand = new Random();
    }

    public int[] reset() {
        System.arraycopy(orig, 0, shuffled, 0, orig.length);
        return shuffled;
    }

    public int[] shuffle() {
        int upperBound = shuffled.length;
        int setInd = 0;
        while (upperBound > 0) {
            int pickInd = setInd + rand.nextInt(upperBound);
            swap(setInd, pickInd, shuffled);
            setInd++;
            upperBound--;
        }
        return shuffled;
    }

    private void swap(int i1, int i2, int[] nums){
        var tmp = nums[i2];
        nums[i2] = nums[i1];
        nums[i1] = tmp;
    }
}



class SolutionRepetit {

    int[] copy;
    int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
        copy = Arrays.copyOfRange(nums, 0, nums.length);
    }

    public int[] reset() {
        return copy;
    }

    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++){
            int ind = randInt(i, nums.length);
            swap(i, ind, nums);
        }
        return nums;
    }

    private int randInt(int lowerBoundInclusive, int upperBoundExclusive){
        return ThreadLocalRandom.current().nextInt(lowerBoundInclusive, upperBoundExclusive);
    }

    private void swap(int i1, int i2, int[] nums){
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}