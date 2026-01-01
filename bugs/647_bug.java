class Solution {
    public int countSubstrings(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            res++;
            int r = i + 1;
            while(i >= 0 && r <= s.length() - 1 && s.charAt(i) == s.charAt(r)){
                res++;
                i--;
                r++;
            }
            r = i + 1;
            int l = i - 1;
            while(l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)){
                res++;
                l--;
                r++;
            }
        }
        return res;
    }
}
