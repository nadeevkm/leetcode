import java.util.Arrays;

class Solution {
    public void rotate1(int[] nums, int k) {  // k = 3,
        if (nums.length % k == 0) {
            for (int i = 0; i < k; i++) { // i = 0,
                var mem = nums[i]; // mem = 4,
                for (int j = i; j < nums.length; j = j + k) { // j = 6,
                    var tmp = nums[(j + k) % nums.length]; // tmp = 4
                    nums[(j + k) % nums.length] = mem; // {1,2,3,1,5,6}
                    mem = tmp; //                         {1,2,3,4,5,6}
                }                                     //  {4,5,6,1,2,3}  // {3,4,5,6,1,2}
            }
        } else {  // k = 3
            int i = 0;
            int p = 0;
            var mem = nums[p];  // mem =
            do {  // p = 4
                var tmp = nums[(p + k) % nums.length]; // tmp =
                nums[(p + k) % nums.length] = mem; //
                mem = tmp; //
                p = (p + k) % nums.length; //
                i++;
//            } while (i != nums.length);
            } while (!Arrays.equals(nums, new int[]{3, 4, 5, 6, 1, 2}));
//            } while (i == 7 );
            var pp = 2;
        }
    }

    public void rotate(int[] nums, int k) {  // k = 3,
        int count = 0;
        for (int startP = 0; count < nums.length; startP++) {
            int p = startP;
            var mem = nums[p];
            do {  // p = 4
                var tmp = nums[(p + k) % nums.length]; // tmp =
                nums[(p + k) % nums.length] = mem; //
                mem = tmp; //
                p = (p + k) % nums.length; //
                count++;
            } while (p != startP);
        }
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();
//        var nums = new int[]{1, 2, 3, 4, 5, 6}; // {5,6,7,1,2,3,4}
//        var k = 4;
//        var nums = new int[]{1, 2, 3, 4, 5, 6}; // {5,6,7,1,2,3,4}
//        var k = 1;
//        var nums = new int[]{1,2,3,4,5,6,7};
//        int k = 3;
        var nums = new int[]{-1,-100,3,99};
        int k = 2;
        sol.rotate(nums, k);
    }
}
