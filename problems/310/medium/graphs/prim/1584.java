import java.util.*;

class Solution { // MST (~ Prim's algo)
    public int minCostConnectPoints(int[][] points) {
        Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]);
        pq.add(new int[] { points[0][0], points[0][1], 0 });
        Set<Point> mst = new HashSet<>();
        int res = 0;

        while (!pq.isEmpty() && mst.size() != points.length) {
            var coordAndWeight = pq.poll();
            var point = new Point(coordAndWeight[0], coordAndWeight[1]);
            if (mst.contains(point)) {
                continue;
            }
            mst.add(point);
            res += coordAndWeight[2];
            for (var neighCoord : points) {
                if (neighCoord[0] == coordAndWeight[0] && neighCoord[1] == coordAndWeight[1]) {
                    continue;
                }
                var neighPoint = new Point(neighCoord[0], neighCoord[1]);
                if (mst.contains(neighPoint)) {
                    continue;
                }
                var dist = Math.abs(coordAndWeight[0] - neighCoord[0]) + Math.abs(coordAndWeight[1] - neighCoord[1]);
                pq.add(new int[] { neighCoord[0], neighCoord[1], dist });
            }
        }
        return res;
    }

    record Point(int x, int y) { };
}


class SolutionPruned { // MST + pruning (Prim's algo)
    public int minCostConnectPoints(int[][] points) {
        Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[2] - p2[2]);
        pq.add(new int[] { points[0][0], points[0][1], 0 });
        Set<Point> mst = new HashSet<>();
        Map<Point, Integer> additionWeights = new HashMap<>(); // pruning
        int res = 0;

        while (!pq.isEmpty() && mst.size() != points.length) {
            var coordAndWeight = pq.poll();
            var point = new Point(coordAndWeight[0], coordAndWeight[1]);
            if (mst.contains(point)) {
                continue;
            }
            mst.add(point);
            res += coordAndWeight[2];
            for (var neighCoord : points) {
                if (neighCoord[0] == coordAndWeight[0] && neighCoord[1] == coordAndWeight[1]) {
                    continue;
                }
                var neighPoint = new Point(neighCoord[0], neighCoord[1]);
                if (mst.contains(neighPoint)) {
                    continue;
                }
                var dist = Math.abs(coordAndWeight[0] - neighCoord[0]) + Math.abs(coordAndWeight[1] - neighCoord[1]);
                if (!additionWeights.containsKey(neighPoint) || additionWeights.get(neighPoint) > dist){ // pruning
                    pq.add(new int[] { neighCoord[0], neighCoord[1], dist });
                    additionWeights.put(neighPoint, dist);
                }
            }
        }
        return res;
    }

    record Point(int x, int y) { };
}


class SolutionOptimal { // Prim without PQ, T = O(n^2) cause graph is compete (E = n^2)
    // !!! works cause all pairs (xi, yi) are distinct, if we could have same points, we could not use HashSet<Point> and Map..
    // => only use index of points[][] then (see 3rd solution)
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] inMST = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // start from node 0
        minDist[0] = 0;

        int total = 0;

        for (int i = 0; i < n; i++) {
            // pick the non-MST node with smallest minDist
            int u = -1;
            int best = Integer.MAX_VALUE;
            for (int v = 0; v < n; v++) {
                if (!inMST[v] && minDist[v] < best) {
                    best = minDist[v];
                    u = v;
                }
            }

            // add it
            inMST[u] = true;
            total += best;

            // relax distances to remaining nodes
            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int cost = Math.abs(points[u][0] - points[v][0])
                            + Math.abs(points[u][1] - points[v][1]);
                    if (cost < minDist[v]) minDist[v] = cost;
                }
            }
        }

        return total;
    }
}

/*

Why PQ hurts here

PriorityQueue helps when:
	•	Graph is sparse
	•	E << V²

But in LeetCode 1584:
	•	Graph is complete
	•	Every node connects to every other node

So:
E = n²
Prim + PQ = O(E log V) = O(n² log n)
Prim without PQ = O(n²)
 */