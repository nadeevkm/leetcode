import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        for (var edg : edges){
            var v1 = edg[0];
            var v2 = edg[1];
            adjMap.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            adjMap.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }

        Queue<Integer> q = new LinkedList<>();
        for (var pair : adjMap.entrySet()){
            if (pair.getValue().size() == 1){
                q.add(pair.getKey());
            }
        }

        while(!q.isEmpty() || adjMap.size() >= 2){
            var v1 = q.poll();
            var v2 = adjMap.get(v1).iterator().next();
            adjMap.remove(v1);
            var neigh2 = adjMap.get(v2);
            neigh2.remove(v1);
            if (neigh2.size() == 1){
                q.add(v2);
            }
        }

        return new LinkedList<Integer>(adjMap.keySet());
    }
}