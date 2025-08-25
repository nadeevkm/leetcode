import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    // Input: intervals = [[1,3],[1,3]]
    // [1,3]
    // [1,3]

    // cs = 1
    // ce = 3
    // Output: [[]]
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[][]{};
        }

        Arrays.sort(intervals, Comparator.comparingInt(inter -> inter[0]));
        List<int[]> res = new ArrayList<>();


        int currStart = intervals[0][0];
        int currEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > currEnd) {
                res.add(new int[]{currStart, currEnd});
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            } else {
            currEnd = Math.max(currEnd, intervals[i][1]);
            }
        }

        res.add(new int[]{currStart, currEnd});

        return res.toArray(new int[][]{});
    }
}

class SolutionLatest {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        var noOverlap = new ArrayList<int[]>();
        noOverlap.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            var lastOne = noOverlap.get(noOverlap.size() - 1);
            if (intervals[i][0] > lastOne[1]){ // not overlap
                noOverlap.add(intervals[i]);
            } else { // overlap
                lastOne[1] = Math.max(lastOne[1], intervals[i][1]);
            }
        }

        // var res = new int[noOverlap.size()][2];
        // for (int i = 0; i < res.length; i++) {
        //     res[i] = noOverlap.get(i);
        // }
        // return res;
        return noOverlap.toArray(new int[][]{});
    }
}