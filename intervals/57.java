import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) { // more clear and concise
        List<int[]> res = new LinkedList<>();
        int n = intervals.length;
        int i = 0;

        // Case 1: No overlapping before merging intervals
        while (i < n && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }

        // Case 2: Overlapping and merging intervals
        while (i < n && newInterval[1] <= intervals[i][0]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Case 3: No overlapping after merging newInterval
        while (i < n){
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[][]{});
    }


    public int[][] insert1(int[][] intervals, int[] newInterval) {
        List<int[]> res = new LinkedList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }
        if (i == intervals.length) {
            res.add(newInterval);
        } else if (newInterval[1] < intervals[i][0]){
            res.add(newInterval);
            for (int j = i; j < intervals.length; j++) {
                res.add(intervals[j]);
            }
        } else {
            res.add(new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])});
            for (int j = i + 1; j < intervals.length; j++) {
                var prevInterv = res.get(res.size() - 1);
                var currInterv = intervals[j];
                if (currInterv[0] > prevInterv[1]) {
                    res.add(currInterv);
                } else {
                    prevInterv[1] = Math.max(prevInterv[1], currInterv[1]);
                }
            }
        }

        return res.toArray(new int[][]{});
    }
}
