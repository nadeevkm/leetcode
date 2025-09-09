class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] post = new int[n];

        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i];
        }

        post[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            post[i] = post[i + 1] * nums[i];
        }

        for (int i = 0; i < n; i++) {
            int first = (i == 0) ? 1 : pre[i - 1];
            int second = (i == n - 1) ? 1 : post[i + 1];
            nums[i] = first * second;
        }

        return nums;
    }
}

class SolutionOpt {
    public int[] productExceptSelf(int[] nums) {  // with only one additional array (result)
        int n = nums.length;
        int[] res = new int[n];

        res[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i];
        }

        for (int i = 1; i < n; i++) {
            nums[i] = nums[i - 1] * nums[i];
        }

        for (int i = 0; i < n; i++) {
            int first = (i == 0) ? 1 : nums[i - 1];
            int second = (i == n - 1) ? 1 : res[i + 1];
            res[i] = first * second; //nums[i-1] * res[i+1]
        }

        return res;
    }
}

class SolutionOptim {
    public int[] productExceptSelf(int[] nums) {  // with only one additional array (result), with 2 passes
        int n = nums.length;
        int[] res = new int[n];

        res[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i];
        }

        int curr = 1;
        for (int i = 0; i < n - 1; i++) {
            res[i] = curr * res[i + 1];
            curr = curr * nums[i];
        }
        res[n-1] = curr;

        return res;
    }
}