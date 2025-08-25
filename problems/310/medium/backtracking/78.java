import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        res.add(new LinkedList<>());
        backtrack(new ArrayList<>(), nums, 0);
        return res;
    }

    public void backtrack(List<Integer> currSet, int[] nums, int p) {
        if (p >= nums.length) { // actually no need to set like this, p == nums.length is enough
            return;
        }
        for (int i = p; i < nums.length; i++) {
            currSet.add(nums[i]);
            res.add(List.copyOf(currSet));
            backtrack(currSet, nums, i + 1);
            currSet.remove(currSet.size() - 1);
        }
    }
}

class SolutionYN { // on each step just decide to take number or not to take it
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(new ArrayList<>(), nums, 0);
        return res;
    }

    public void backtrack(List<Integer> currSet, int[] nums, int p) {
        if (p == nums.length) {
            res.add(new LinkedList<>(currSet));
            return;
        }
        currSet.add(nums[p]);
        backtrack(currSet, nums, p + 1 );

        currSet.remove(currSet.size() - 1);
        backtrack(currSet, nums, p + 1);
    }
}

class SolutionIterative { // add number to all possible prev choices, can think of ot as a kind DP-one

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new LinkedList<>());
        for (int n : nums){
            int size = res.size(); // ! need to extract from for loop, couse size is going to change every loop
            for (int i = 0; i < size; i++){
                var newSet = new LinkedList<>(res.get(i));
                newSet.add(n);
                res.add(newSet);
            }
        }
        return res;
    }
}
