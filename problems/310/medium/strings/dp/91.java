class Solution {
    public int numDecodings(String s) {
        // 11106 -> (1110) 6 + 111 (06) -> check validness '6' and '06' and sum number of ways
        // 26 -> 2 6 | 26
        // 06 -> 0 6
        int[] mem = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int opt1 = i - 1 >= 0 ? mem[i - 1] : 1;
            int opt2 = i - 2 >= 0 ? mem[i - 2] : 1;
            if (s.charAt(i) != '0'){
                mem[i] += opt1;
            }
            if (i >= 1) {
                var groupedInt = Integer.valueOf(s.substring(i - 1, i + 1));
                if (groupedInt >= 10 && groupedInt <= 26) {
                    mem[i] += opt2;
                }
            }
        }
        return mem[s.length() - 1];
    }
}