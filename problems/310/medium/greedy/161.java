
    /*

    1) eq length && one letter differs
    2) length differs by 1 && all letters equal except one 

only three possible cases

1)    ..a..
      ..b..

2)    .a..
      ...

3)   ...a
     ...

    abc
    ab

    1203
    1213

     */

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()){
            return isOneEditDistance(t, s);
        }

        if (t.length() - s.length() > 1){
            return false;
        }

        int diff = 0;
        if (s.length() == t.length()){
            for (int i = 0; i < s.length(); i++){
                if (s.charAt(i) != t.charAt(i)){
                    diff++;
                }
                if (diff > 1){
                    return false;
                }
            }
        } else {
            for (int i = 0; i < t.length(); i++) {
                // need to check all letters of longer string against shorter one with possible shift  - ex. ("abc", "abXd") should be false
                // and according point to the shorter string letter should be shifted to the current diff
                if (i - diff == s.length() || s.charAt(i - diff) != t.charAt(i)) {
                    diff++;
                }
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1; // cause return true only when two strings are exactly one edit apart (absolute equal (diff == 0) strings => return false)
    }
}

class Check{
    public static void main(String[] args) {
        var r1 = new Solution().isOneEditDistance("ab", "acb");
        var r2 = new Solution().isOneEditDistance("cab", "ad");
        var r3 = new Solution().isOneEditDistance("1203", "1213");
        var r4 = new Solution().isOneEditDistance("", "1");
        var r5 = new Solution().isOneEditDistance("1", "");
        var r6 = new Solution().isOneEditDistance("1111", "111");
        var r7 = new Solution().isOneEditDistance("1111", "1114");
        var r8 = new Solution().isOneEditDistance("1115", "111");
        var r9 = new Solution().isOneEditDistance("1211", "1114");
        var r10 = new Solution().isOneEditDistance("1211", "111");
        
        var r11 = new Solution().isOneEditDistance("ab", "cab"); // t
        var r12 = new Solution().isOneEditDistance("ab", "abc"); // t
        var r13 = new Solution().isOneEditDistance("ab", "acb"); // t
        var r14 = new Solution().isOneEditDistance("abcd", "acbd"); // f
        var r15 = new Solution().isOneEditDistance("abc", "abXd"); // f
    }
}