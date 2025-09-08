import java.util.HashSet;

class Solution {
    public int longestConsecutive1(int[] nums) {
        var mapNums = new HashSet<Integer>();
        for (var num : nums){
            mapNums.add(num);
        }
        var candid = 0;
        for (var num : nums){
            mapNums.remove(num);
            var length = 1;
            for (int i = 1; mapNums.contains(num - i); i++){
                mapNums.remove(num - i);
                length++;
            }
            for (int i = 1; mapNums.contains(num + i); i++){
                mapNums.remove(num - i);
                length++;
            }
            candid = Math.max(candid, length);
        }
        return candid;
    }

    public int longestConsecutive(int[] nums) {
        var mapNums = new HashSet<Integer>();
        for (var num : nums){
            mapNums.add(num);
        }
        var candid = 0;
        for (var num : nums){
            if (!mapNums.contains(num - 1)) { // means that it is a start of an interval
                var length = 1;
                for (int i = 1; mapNums.contains(num + i); i++) {
                    length++;
                }
                candid = Math.max(candid, length);
            }
        }
        return candid;
    }
}


class SolutionRepetit {
    public int longestConsecutive(int[] nums) {
        var uniqNum = new HashSet<Integer>();
        for (int n : nums){
            uniqNum.add(n);
        }
        int res = 0;
        while (!uniqNum.isEmpty()){  // gives TLE, need to replace with    ->
            var curr = uniqNum.iterator().next();   // replace with   -> for (var curr : nums) {
            uniqNum.remove(curr);   //  ->       if (uniqNum.remove(curr)) {
            var left = curr;
            var right = curr;
            while (uniqNum.contains(left - 1)){
                left = left - 1;
                uniqNum.remove(left);
            }
            while (uniqNum.contains(right + 1)){
                right = right + 1;
                uniqNum.remove(right);
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}

class SolutionBrilliant{ // check only possible starts of the sequences (if we don't have num - 1, then it is a start) => then no need of removing numbers, just count
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}