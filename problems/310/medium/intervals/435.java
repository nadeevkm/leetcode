class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        var curr = intervals[0];
        int toRemove = 0;
        for (int i = 1; i < intervals.length; i++){
            var next = intervals[i];
            if (next[0] >= curr[1]){
                curr = next;
            } else {
                toRemove++;
                curr[1] = Math.min(curr[1], next[1]);
            }
        }
        return toRemove;
    }
}
