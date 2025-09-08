import java.util.HashMap;
import java.util.Map;

class Solution { // use prefix sum, reduce brute force O(n^n) to O(n^2)
    public int subarraySum(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                res++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}


class SolutionHashMap { // improve previous with a hashMap and 'two sum' idea to O(n)
    // but you need not to create map once and check it afterwards, but add values (from the end) and check it according
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] cumul = new int[n];
        cumul[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cumul[i] = nums[i] + cumul[i - 1];
        }
        int res = 0;
        Map<Integer, Integer> sums = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            sums.put(cumul[i], sums.getOrDefault(cumul[i], 0) + 1);
            int target = (i - 1 == -1) ? k : (k + cumul[i - 1]);
            res += sums.getOrDefault(target, 0);
        }
        return res;
    }
}

class Check {
    public static void main(String[] args) {
        var res = new SolutionHashMap().subarraySum(new int[]{-1, -1, 1}, 0);
    }
}





import java.util.*;

class SolutionTleList { // O(n^2)
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        List<Integer>[] mem = new List[nums.length];
        Arrays.setAll(mem, i -> new ArrayList<>());
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == k){
                cnt++;
            }
            mem[i].add(nums[i]);
            if (i == 0) {
                continue;
            }
            for (var sum : mem[i-1]){
                if (sum + nums[i] == k){
                    cnt++;
                }
                mem[i].add(sum + nums[i]);
            }
        }
        return cnt;
    }
}

class SolutionTleArray { // O(n^2)
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[][] mem = new int[n][n];
        mem[0][0] = nums[0];
        int cnt = mem[0][0] == k ? 1 : 0;
        for (int r = 1; r < n; r++){
            if (nums[r] == k){
                cnt++;
            }
            mem[r][r] = nums[r];
            for (int c = r - 1; c >= 0; c--){
                var sum = mem[r-1][c] + nums[r];
                mem[r][c] = sum;
                if (sum == k){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class SolutionCumulativeSum { // T = O(n^2), S = O(1)
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int cumSum = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++){
            cumSum += nums[i];
            if (cumSum == k){
                cnt++;
            }
            int currSum = cumSum;
            for (int j = 0; j < i; j++){
                currSum -= nums[j];
                if (currSum == k){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class SolutionMostOptimalHashMap { // T = O(n), S = O(n)
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        Map<Integer, Integer> freqCumSums = new HashMap<>();
        int cumulSums = 0;
        for (int i = 0; i < n; i++){
            cumulSums += nums[i];
            freqCumSums.put(cumulSums, freqCumSums.getOrDefault(cumulSums, 0) + 1);
        }
        int cumDecr = 0;
        for (int i = 0; i < n; i++){
            cnt += freqCumSums.getOrDefault(cumDecr + k, 0);
            cumDecr += nums[i];
        }
        return cnt;
    }
}