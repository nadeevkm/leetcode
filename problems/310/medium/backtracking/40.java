class Solution {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(0, candidates, target, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int ind, int[] candidates, int target, int sum, List<Integer> comb){
        if (sum == target){
            res.add(new LinkedList(comb));
            return;
        }
        if (sum > target){
            return;
        }
        if (ind == candidates.length){
            return;
        }

        var cand = candidates[ind];
        comb.add(cand);
        dfs(ind + 1, candidates, target, sum + cand, comb);
        comb.remove(comb.size() - 1);

        var nextInd = ind + 1;
        while (nextInd < candidates.length && candidates[nextInd] == cand){
            nextInd++;
        }
        dfs(nextInd, candidates, target, sum, comb);
    }
}