class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (var e : edges){
            adjMap.computeIfAbsent(e[0], k -> new LinkedList<>())
                    .add(e[1]);
            adjMap.computeIfAbsent(e[1], k -> new LinkedList<>())
                    .add(e[0]);
        }

        Set<Integer> visited = new HashSet<>();
        if (!dfs(0, adjMap, visited, -1)){
            return false;
        }

        return visited.size() == n;
    }

    private boolean dfs(int v, Map<Integer, List<Integer>> adjMap, Set<Integer> visited, int prev){
        if (visited.contains(v)){
            return false;
        }
        visited.add(v);
        for (int n : adjMap.getOrDefault(v, new LinkedList<>())){
            if (n == prev){
                continue;
            }
            if (!dfs(n, adjMap, visited, v)){
                return false;
            }
        }
        return true;
    }
}
