class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] mem = new boolean[s.length()];
        for (int end = 0; end < s.length(); end++){
            for (int st = end; st >= 0; st--){
                var w = s.substring(st, end + 1);
                if (wordDict.contains(w) && (st == 0 || mem[st - 1] == true)){
                    mem[end] = true;
                    break;
                }
            }
        }
        return mem[s.length() - 1];
    }
}

class SolutionOpt { // O(n^2) => O(n*m)
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] mem = new boolean[s.length()];
        for (int end = 0; end < s.length(); end++) {
            for (var dictWord : wordDict){
                var st = end - dictWord.length() + 1;
                if (st >= 0){
                    var word = s.substring(st, end + 1);
                    if (word.equals(dictWord) && (st == 0 || mem[st - 1] == true)){
                        mem[end] = true;
                        break;
                    }
                }
            }
        }
        return mem[s.length() - 1];
    }
}