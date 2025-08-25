import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {

    // [-1,0,1,2,-1,-4]
    //            lp
    //         i        rp
    //   0  1  2  3  4  5
    // [-4,-1,-1, 0, 1, 2]
    // s = 1
    // cs = 2
    // res = [-1, -1, 2], [-1, 0, 1],
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);

        int lp;
        int rp;
        int sum;
        int curSum;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            sum = -1 * nums[i];
            lp = i + 1;
            rp = n - 1;

            while (lp < rp) {
                if (lp > i + 1 && nums[lp] == nums[lp - 1]){
                    lp++;
                    continue;
                }
                if (rp < n - 1 && nums[rp] == nums[rp + 1]){
                    rp--;
                    continue;
                }
                curSum = nums[lp] + nums[rp];
                if (curSum < sum) {
                    lp++;
                } else if (curSum > sum) {
                    rp--;
                } else { // curSum == sum
                    res.add(Arrays.asList(nums[i], nums[rp], nums[lp]));
                    lp++;
                }
            }
        }

        return res;
    }
}

class Check{
    public static void main(String[] args) {
        var res1 = new Solution().threeSum(new int[]{-1,0,1,2,-1,-4});
        var res2 = new Solution().threeSum(new int[]{0, 0, 0});
        var res3 = new Solution().threeSum(new int[]{0, 1, 1});
        var res4 = new Solution().threeSum(new int[]{0, 1});
        int pp = 2;
    }
}
