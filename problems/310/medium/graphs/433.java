import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>();
        for (String w : bank){
            bankSet.add(w);
        }
        if (!bankSet.contains(endGene)){
            return -1;
        }
        char[] abc = {'A', 'C', 'G', 'T'};

        Deque<String> q = new LinkedList<>();
//        Set<String> marked = new HashSet<>();
        q.add(startGene);
//        marked.add(startGene);

        int level = 0;
        while(!q.isEmpty()){

            int size = q.size();
            while (size-- > 0){
                String mut = q.poll();
                if (mut.equals(endGene)){
                    return level;
                }

                for (int i = 0; i < mut.length(); i++){
                    for (char let : abc){
                        char[] mutArr = mut.toCharArray();
                        mutArr[i] = let;
                        String cand = new String(mutArr);
//                        if (bankSet.contains(cand) && !marked.contains(cand)){
                        if (bankSet.contains(cand)){
                            q.add(cand);
//                            marked.add(cand);
                            bankSet.remove(cand);
                        }
                    }
                }
            }
            level++;
        }

        return -1;
    }
}

class SolutionRepetit {

    char[] genes = new char[]{'A', 'C', 'G', 'T'};

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> mutations = new HashSet<String>();
        for (var mut : bank){
            mutations.add(mut);
        }
        if (!mutations.contains(endGene)){
            return -1;
        }

        Queue<String> q = new LinkedList<>();
        q.add(startGene);
        mutations.remove(startGene);
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size -- > 0){
                var mut = q.poll();
                //var sb = new StringBuilder(mut); --> better to do by chars

                for (int i = 0; i < mut.length(); i++){
                    for (var ch : genes){
                        //sb.setCharAt(i, ch);

                        //var nextMut = sb.toString();

                        char[] nextMutChars = mut.toCharArray();
                        nextMutChars[i] = ch;
                        var nextMut = new String(nextMutChars);

                        if (nextMut.equals(endGene)){
                            return level + 1;
                        }

                        if (mutations.contains(nextMut)){
                            mutations.remove(nextMut);
                            q.add(nextMut);
                        }

                        //sb.setCharAt(i, mut.charAt(i));
                    }
                }
            }
            level++;
        }
        return -1;
    }
}