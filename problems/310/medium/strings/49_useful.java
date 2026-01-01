class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for(var w : strs){
            var sorted = sort(w);
            groups.computeIfAbsent(sorted, k -> new LinkedList<>()).add(w);
        }
        List<List<String>> res = new LinkedList<>();
        for (var list : groups.values()){
            res.add(list);
        }
        return res;
    }

    private String sort(String s){
        var chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}

class SolutionOpt {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (var w : strs) {
            var key = key(w);
            groups.computeIfAbsent(key, k -> new LinkedList<>()).add(w);
        }
        return new LinkedList(groups.values());
    }

    private String key(String s) {
        char[] keyCharSet = new char[26];
        for (var ch : s.toCharArray()){
            keyCharSet[ch - 'a']++;
        }
        return String.valueOf(keyCharSet);
    }
}