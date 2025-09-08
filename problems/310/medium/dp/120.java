class Solution {
    //   r1  i0
    // [[2],[3,4],[6,5,7],[4,1,8,3]]
    // 0 1 2 3
    // 5 6 0 0
    //
    //
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] mem = new int[triangle.size()];
        mem[0] = triangle.get(0).get(0);
        for (int r = 1; r < triangle.size(); r++){
            var row = triangle.get(r);
            for (int i = row.size() - 1; i >= 0; i--){
                int prevMin = Math.min(
                        i == row.size() - 1 ? Integer.MAX_VALUE : mem[i],
                        i == 0 ? Integer.MAX_VALUE : mem[i-1]
                );
                mem[i] = prevMin + row.get(i);
            }
        }
        return min(mem);
    }

    public int min(int[] nums){
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            min = Math.min(min, num);
        }
        return min;
    }
}

class SolutionRepetit {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int r = triangle.size() - 2; r >= 0; r--) {
            for (int c = 0; c <= r; c++) {
                int minBelow = Math.min(triangle.get(r + 1).get(c), triangle.get(r + 1).get(c + 1));
                triangle.get(r).set(c, triangle.get(r).get(c) + minBelow);
            }
        }
        return triangle.get(0).get(0);
    }
}

// without triangle modification
class SolutionWithSpace {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> belowRow = triangle.get(triangle.size() - 1);
        for (int r = triangle.size() - 2; r >= 0; r--) {
            List<Integer> curRow = new LinkedList<>();
            for (int c = 0; c <= r; c++) {
                int minBelow = Math.min(belowRow.get(c), belowRow.get(c + 1));
                curRow.add(triangle.get(r).get(c) + minBelow);
            }
            belowRow = curRow;
        }
        return belowRow.get(0);
    }
}