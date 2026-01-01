import java.util.*;

class WordDistance {

    Map<String, List<Integer>> positions = new HashMap<>();

    public WordDistance(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++){
            var w = wordsDict[i];
            positions.computeIfAbsent(w, k -> new LinkedList<>())
                    .add(i);
        }
    }

    public int shortest(String word1, String word2) {
        var positions1 = positions.get(word1);
        int p1 = 0;
        var positions2 = positions.get(word2);
        int p2 = 0;
        int res = Integer.MAX_VALUE;
        while (p1 != positions1.size() - 1 || p2 != positions2.size() - 1){
            var pos1 = positions1.get(p1);
            var pos2 = positions2.get(p2);
            res = Math.min(res, Math.abs(pos2 - pos1));
            if (p1 < p2){
                p1++;
            } else {
                p2++;
            }
        }
        return res;
    }
}