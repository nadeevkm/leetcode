import java.util.*;

class Solution {

    public List<List<Integer>> combine(int n, int k) {
        return pick(new ArrayList<List<Integer>>(), 1, new Integer[k], 0, n, k);
    }

    /*
        pick one every iteration, narrow set of candidates for further calls
     */
    public List<List<Integer>> pick(List<List<Integer>> overallList, int startNum, Integer[] curr, int cp, int n, int k) {
        if (cp == k) {
            overallList.add(List.of(curr));
        } else {
            for (int i = startNum; i <= n; i++) {
                curr[cp] = i;
                pick(overallList, i + 1, curr, cp + 1, n, k);
            }
        }
        return overallList;
    }

//    public List<List<Integer>> combine(int n, int k) {
//        var set = new HashSet<Integer>();
//        for (int i = 1; i <= n; i++){
//            set.add(i);
//        }
//        return combine(new ArrayList<List<Integer>>(), set, new HashSet<>(), k);
//    }

    public List<List<Integer>> combine(List<List<Integer>> overallList, Set<Integer> toPick, Set<Integer> curr, int k) { // pick or not pick solution, all paths
        if (curr.size() == k) {
            overallList.add(new ArrayList<>(curr));
            return overallList;
        }

        if (toPick.size() < (k - curr.size())) {
            return overallList;
        }

        var cand = toPick.iterator().next();
        toPick.remove(cand);

        curr.add(cand);
        combine(overallList, toPick, curr, k);

        curr.remove(cand);
        combine(overallList, toPick, curr, k);

        toPick.add(cand);

        return overallList;
    }
}

class SolutionRepetetion {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, new ArrayList<Integer>(), 1);
        return res;
    }

    private void backtrack(int n, int k, List<Integer> curr, int stNum) {
        if (curr.size() == k) {
            res.add(new ArrayList(curr));
            return;
        }

        for (int num = stNum; num <= n; num++) {
            curr.add(num);
            backtrack(n, k, curr, num + 1);
            curr.remove(curr.size() - 1);
        }
    }
}





class Check {
    public static void main(String[] args) {
        var pp = new Solution().combine(4, 2);
    }
}