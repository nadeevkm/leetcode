import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return permuteRec(new ArrayList<>(), nums, new Integer[nums.length], 0);
    }

    /*
        improved solution, reverse path on every FOR loop iteration, remove excess array iterations by swapping used elements with not yet used
     */
    public List<List<Integer>> permuteRec(List<List<Integer>> overallList, int[] nums, Integer[] currPerm, int curr) {
        if (curr == nums.length) {
            overallList.add(List.of(currPerm));
        } else {
            for (int j = curr; j < nums.length; j++) {
                currPerm[curr] = nums[j];
                swap(nums, curr, j);
                curr++;
                permuteRec(overallList, nums, currPerm, curr);
                curr--;
                swap(nums, j, curr);
            }
        }
        return overallList;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    //    public List<List<Integer>> permute(int[] nums) {
//        return permuteRec(new ArrayList<>(), new ArrayList<>(), nums);
//    }
    public List<List<Integer>> permuteRec(List<List<Integer>> overallList, List<Integer> currPerm, int[] nums) { // initial solution
        if (currPerm.size() == nums.length) {
            overallList.add(currPerm);
        } else {
            for (int n : nums) {
                if (!currPerm.contains(n)) {
                    var newPerm = new ArrayList<Integer>(currPerm);
                    newPerm.add(n);
                    permuteRec(overallList, newPerm, nums);
                }
            }
        }
        return overallList;
    }
}
