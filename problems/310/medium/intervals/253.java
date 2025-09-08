import java.util.*;

class Interval {
    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort((i1, i2) -> i1.start - i2.start);
        Queue<Interval> pq = new PriorityQueue<Interval>((i1, i2) -> i1.end - i2.end);
        int maxIntersections = 0;
        for (var interv : intervals){
//            while (!pq.isEmpty() && pq.peek().end <= interv.start ){
            if (!pq.isEmpty() && pq.peek().end <= interv.start ){
                pq.poll();
            }
            pq.add(interv);
//            maxIntersections = Math.max(maxIntersections, pq.size()); // actually we will never decrease size of PQ, so we can safely return pq.size()
        }
        return pq.size();
//        return maxIntersections;
    }
}

class Solution2 { // just go through all the points and add overlap interval count is this point is start of some interval and decrease if it is end
    // note it can be start or end of several intervals at once
    public int minMeetingRooms(List<Interval> intervals) {
        Map<Integer, Integer> starts = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();
        List<Integer> points = new ArrayList<>();
        for (var inter : intervals){
            var startsCount = starts.getOrDefault(inter.start, 0);
            var endsCount = ends.getOrDefault(inter.end, 0);
            if (startsCount == 0 && !ends.containsKey(inter.start)){
                points.add(inter.start);
            }
            if (endsCount == 0 && !starts.containsKey(inter.end)){
                points.add(inter.end);
            }
            starts.put(inter.start, startsCount + 1);
            ends.put(inter.end, endsCount + 1);
        }
        points.sort((n1, n2) -> n1 - n2);
        int maxIntersection = 0;
        int currIntersections = 0;
        for (int point : points){
            int startsCount = starts.getOrDefault(point, 0);
            int endsCount = ends.getOrDefault(point, 0);
            currIntersections = currIntersections + startsCount - endsCount;
            maxIntersection = Math.max(maxIntersection, currIntersections);
        }
        return maxIntersection;
    }
}

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