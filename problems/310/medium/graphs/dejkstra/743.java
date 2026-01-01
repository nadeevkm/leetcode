import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for (var time : times) {
            adjMap.computeIfAbsent(time[0], key -> new LinkedList<int[]>())
                    .add(new int[] { time[1], time[2] });
        }

        Queue<int[]> pq = new PriorityQueue<int[]>((i1, i2) -> i1[1] - i2[1]);
        Set<Integer> reached = new HashSet<>();
        pq.add(new int[] { k, 0 });
        reached.add(k);

        int res = 0;
        while (!pq.isEmpty()) {
            var curr = pq.poll();
            for (var neigTime : adjMap.getOrDefault(curr[0], new LinkedList<>())) {
                var neig = neigTime[0];
                var time = neigTime[1];
                if (!reached.contains(neig)) {
                    var dist = curr[1] + time;
                    pq.add(new int[] { neig, dist });
                    reached.add(neig);
                    if (reached.size() == n){
                        return res;
                    }
                }
            }
        }

        return reached.size() == n ? res : -1;
    }
}