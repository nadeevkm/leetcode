import java.util.*;

class Solution { // avoid duplicates by sorting nums and skipping the same numbers

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, boolean[] usedInd, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        int lastUsed = Integer.MIN_VALUE;
        for (int i = 0; i < usedInd.length; i++) {
            if (!usedInd[i] && nums[i] != lastUsed) {
                lastUsed = nums[i];
                path.add(nums[i]);
                usedInd[i] = true;
                backtrack(nums, usedInd, path);
                usedInd[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}

class SolutionHashing { // avoid duplicates by using only unique numbers as an option (create hashmap with counter)

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (var n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }
        backtrack(nums, counter, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, Map<Integer, Integer> counter, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (var num : counter.keySet()) {
            if (counter.get(num) == 0) {
                continue;
            }
            path.add(num);
            counter.put(num, counter.get(num) - 1);
            backtrack(nums, counter, path);
            counter.put(num, counter.get(num) + 1);
            path.remove(path.size() - 1);
        }
    }
}
