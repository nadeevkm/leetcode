import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int lp = 0;
        int rp = 1;
        var charSet = new HashSet<Character>();
        charSet.add(s.charAt(lp));
        int candidate = 1;
        //      012345
        // s = "pwwkew";
        while (rp < s.length()){  // cand 2, lp 2, rp 3, charset -> pk
            if(charSet.contains(s.charAt(rp))){
                while(s.charAt(lp) != s.charAt(rp)) {
                    charSet.remove(s.charAt(lp));
                    lp++;
                }
                lp++;
            } else {
                charSet.add(s.charAt(rp));
            }
            candidate = Math.max(candidate, charSet.size());
            rp++;
        }
        return candidate;
    }
}

class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var s = "abcabcbb";
        var pp = sol.lengthOfLongestSubstring(s);
    }
}
