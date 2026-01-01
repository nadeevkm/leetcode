import java.util.Random;

class Solution {

    int[] helper;
    int bound;
    Random rand;

    public Solution(int[] w) {
        rand = new Random();
        helper = new int[w.length];
        int acc = 0;
        for (int i = 0; i < w.length; i++){
            acc += w[i];
            helper[i] = acc - 1;
        }
        bound = acc;
    }

    //               0 1 2 3 4 5 6 7
    // [2,3,1,2] -> [0,0,1,1,1,2,3,3]
    // i.e. so 0-1 should give 0, 2-4 give 1, 5 give 2, 6-7 give 3
    // => convert array to
    // [1,4,6,7]
    // and use binary search with random target (sum of w is upper bound)
    public int pickIndex() {
        int target = rand.nextInt(bound);
        int l = 0;
        int r = helper.length - 1;
        while (l <= r){
            int m = r + (l - r)/2;
            if (helper[m] == target){
                return m;
            } else if (helper[m] < target){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */