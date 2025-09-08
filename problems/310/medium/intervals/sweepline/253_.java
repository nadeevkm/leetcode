class Solution2Optimal { // ~SweepLine technic, we can improve 2nd approach, by summarizing all actions within points map population
    public int minMeetingRooms(List<Interval> intervals) {
        Map<Integer, Integer> points = new TreeMap<>(); // thus we will have keys as a points in order
        for (var interv : intervals){
            points.put(interv.start, points.getOrDefault(interv.start, 0) + 1);
            points.put(interv.end, points.getOrDefault(interv.end, 0) - 1);
        }
        int maxOverlapCount = 0;
        int currIntersection = 0;
        for (var point : points.keySet()){
            currIntersection += points.get(point);
            maxOverlapCount = Math.max(maxOverlapCount, currIntersection);
        }
        return maxOverlapCount;
    }
}


class Solution2OptimalArrays { // ~SweepLine technic, we can improve 2nd approach, by summarizing all actions within points map population
    public int minMeetingRooms(int[][] intervals) {
        Map<Integer, Integer> points = new TreeMap<>(); // thus we will have keys as a points in order
        for (var interv : intervals){
            points.put(interv[0], points.getOrDefault(interv[0], 0) + 1);
            points.put(interv[1], points.getOrDefault(interv[1], 0) - 1);
        }
        int maxOverlapCount = 0;
        int currIntersection = 0;
        for (var point : points.keySet()){
            currIntersection += points.get(point);
            maxOverlapCount = Math.max(maxOverlapCount, currIntersection);
        }
        return maxOverlapCount;
    }
}