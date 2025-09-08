class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combine(new ArrayList<List<Integer>>(), candidates, 0, new ArrayList<Integer>(), 0, target);
    }


    //
    // [2,3,6,7], target = 7

    // 2 + 2 + 2 + 2 / 8
    // 2 + 2 + 2 + 3 / 9
    // ..
    // 2 + 2 + 3 / 7
    // 2 + 2 + 6 /
    // ..
    // 2 + 3 + 2 (should be >= index 3)  / 7  <---- should start with 3 (last 2->3 )
    // 2 + 3 + 3
    public List<List<Integer>> combine(List<List<Integer>> overallSum, int[] candidates, int startIndex, List<Integer> curr, int sum, int target){
        if (sum > target){
            return overallSum;
        }

        if(sum == target){
            overallSum.add(new ArrayList<Integer>(curr));
            return overallSum;
        }

        for (int i = startIndex; i <= candidates.length; i++) {
            var cand = candidates[i];
            curr.add(cand);
            sum = sum + cand;
            combine(overallSum, candidates, i, curr, sum, target);
            sum = sum - cand;
            curr.remove(curr.size() - 1);
        }

        return overallSum;
    }
}

class SolutionRepetetion {

    int[] candidates;
    int target;
    List<List<Integer>> res = new LinkedList();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.target = target;
        backtrack(new ArrayList(), 0, 0);
        return res;
    }

    private void backtrack(List<Integer> curr, int sum, int ind) {
        if (sum > target || ind == candidates.length) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList(curr));
            return;
        }
        
        // don't take
        backtrack(curr, sum, ind + 1);

        // take
        curr.add(candidates[ind]);
        sum += candidates[ind];
        backtrack(curr, sum, ind);
        curr.remove(curr.size() - 1);
        sum -= candidates[ind];
    }
}