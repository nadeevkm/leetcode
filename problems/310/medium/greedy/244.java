import java.util.*;

class WordDistance {

    Map<String, List<Integer>> idxs;

    public WordDistance(String[] wordsDict) {
        idxs = new HashMap<>();
        for (int i = 0; i < wordsDict.length; i++){
            idxs.computeIfAbsent(wordsDict[i], (k) -> new ArrayList<>())
                            .add(i);
        }
    }

    public int shortest(String word1, String word2) {
        var indList1 = idxs.get(word1);
        var indList2 = idxs.get(word2);
        int dist = Integer.MAX_VALUE;
        int i1 = 0;
        int i2 = 0;
        while (i1 < indList1.size() && i2 < indList2.size()){
            dist = Math.min(Math.abs(indList1.get(i1) - indList2.get(i2)), dist);
            if (indList1.get(i1) > indList2.get(i2)){
                i2++;
            } else {
                i1++;
            }
        }
        return dist;
    }
}

class WordDistanceRepetit {

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
        while (p1 < positions1.size() && p2 < positions2.size()) { // exit when one of the pointers goes out of scope
            var pos1 = positions1.get(p1);
            var pos2 = positions2.get(p2);
            res = Math.min(res, Math.abs(pos2 - pos1));
            if (pos1 < pos2){
                p1++;
            } else {
                p2++;
            }
        }
        return res;
    }
}