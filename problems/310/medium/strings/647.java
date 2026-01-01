class Solution {
    public int countSubstrings(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            res++;
            int l = i;
            int r = i + 1;
            while(l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)){
                res++;
                l--;
                r++;
            }
            r = i + 1;
            l = i - 1;
            while(l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)){
                res++;
                l--;
                r++;
            }
        }
        return res;
    }
}