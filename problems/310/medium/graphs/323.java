import java.util.*;

class Solution {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();
        for (var e : edges) {
            var v1 = e[0];
            var v2 = e[1];
            edgesMap.computeIfAbsent(v1, k -> new LinkedList<>()).add(v2);
            edgesMap.computeIfAbsent(v2, k -> new LinkedList<>()).add(v1);
        }
        Set<Integer> notVisited = new HashSet<>();
        for (int i = 0; i < n; i++){
            notVisited.add(i);
        }
        int comp = 0;
        while (!notVisited.isEmpty()) {
            dfs(notVisited.iterator().next(), edgesMap, notVisited);
            comp++;
        }
        return comp;
    }

    private void dfs(int v, Map<Integer, List<Integer>> edgesMap, Set<Integer> notVisited) {
        if (!notVisited.contains(v)) {
            return;
        }
        notVisited.remove(v);
        for (var neigh : edgesMap.getOrDefault(v, Collections.emptyList())) { // !!! need to add empty list to avoid NPE when empty edges provided
            dfs(neigh, edgesMap, notVisited);
        }
    }
}


class SolutionUnionFind {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++){
            roots[i] = i;
        }
        int[] ranks = new int[n];

        int count = n;
        for (var e : edges){
            count -= union(roots, ranks, e[0], e[1]);
        }
        return count;
    }

    private int find(int[] roots, int val){
        int root = roots[val];
        while (root != val){
            int prevVal = val;  //1
            val = root;
            root = roots[root];
            roots[prevVal] = root; //2  (1) and (2) optimizations for path compression
        }
        return root;
    }

    private int union(int[] roots, int[] ranks, int val1, int val2){
        int root1 = find(roots, val1);
        int root2 = find(roots, val2);

        if (root1 == root2){
            return 0; // already connected, no need to do that again and treat it as a new found join
        }

        if (ranks[root1] > ranks[root2]){
            roots[root2] = root1;
            ranks[root1] += ranks[root2];
        } else {
            roots[root1] = root2;
            ranks[root2] += ranks[root1];
        }
        return 1;
    }
}

class SolutionRepetit {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (var edg : edges){
            adjMap.computeIfAbsent(edg[0], k -> new LinkedList<Integer>())
                    .add(edg[1]);
            adjMap.computeIfAbsent(edg[1], k -> new LinkedList<Integer>())
                    .add(edg[0]);
        }

        int cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(i, adjMap, visited);
                cnt++;
            }
        }
        return cnt;
    }

    private void dfs(int v, Map<Integer, List<Integer>> adjMap, boolean[] visited){
        if (visited[v]){
            return;
        }
        visited[v] = true;
        for (var neigh : adjMap.getOrDefault(v, Collections.emptyList())){
            dfs(neigh, adjMap, visited);
        }
    }
}