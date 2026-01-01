class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (var num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (var num : nums) {
            var pair = k - num;
            var countNum = freq.get(num);
            var countPair = freq.getOrDefault(pair, 0);
            if (num != pair) {
                if (countNum >= 1 && countPair >= 1) {
                    freq.put(num, countNum - 1);
                    freq.put(pair, countPair - 1);
                    res++;
                }
            } else { // num == pair
                if (countNum >= 2) {
                    freq.put(num, countNum - 2);
                    res++;
                }
            }
        }
        return res;
    }
}

class Solution {
    public int maxOperations(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> seenNonPairedCount = new HashMap<>();
        for (int n : nums){
            int paired = k - n;
            if (!seenNonPairedCount.containsKey(paired)){
                seenNonPairedCount.put(n, seenNonPairedCount.getOrDefault(n, 0) + 1);
            } else {
                res++;
                var cnt = seenNonPairedCount.get(paired);
                if (cnt == 1) {
                    seenNonPairedCount.remove(paired);
                } else {
                    seenNonPairedCount.put(paired, cnt - 1);
                }
            }
        }
        return res;
    }
}