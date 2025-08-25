import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strNums = new String[nums.length];
        for (int i = 0; i < n; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums,
                (s1, s2) -> s1.length() == s2.length() ? s1.compareTo(s2) : (s1 + s2).compareTo(s2 + s1)
        );
        var sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(strNums[i]);
        }
        return sb.charAt(0) == '0' ? "0" : sb.toString(); // i.e. [0,0,0] should resulted in "0", not in "000"
    }
}

class SolutionLatest {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] numsString = new String[nums.length];
        for (int i = 0; i < n; i++) {
            numsString[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsString,
                (n1, n2) -> (n2 + n1).compareTo(n1 + n2)
        );
        var sb = new StringBuilder();
        for (var num : numsString){
            sb.append(num);
        }
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}


class Check {
    public static void main(String[] args) {
//        new Solution().largestNumber(new int[]{3,30,34,5,9});
//        new Solution().largestNumber(new int[]{0, 301, 30, 34, 5, 5, 900, 9, 90025, 9009});
        new Solution().largestNumber(new int[]{900, 90099, 9009, 15511, 155, 1551});
    }
}