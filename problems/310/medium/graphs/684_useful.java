class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Map<Edge, Integer> edgMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++){
            var edg = edges[i];
            edgMap.put(new Edge(edg[0], edg[1]), i);
            edgMap.put(new Edge(edg[1], edg[0]), i);
            adjMap.computeIfAbsent(edg[0], k -> new LinkedList<>()).add(edg[1]);
            adjMap.computeIfAbsent(edg[1], k -> new LinkedList<>()).add(edg[0]);
        }

        var resEdge = dfs(1, adjMap, new HashMap<Integer,Integer>(), 0, new LinkedList<Edge>(), edgMap);
        for (var edg : edges){
            if ((edg[0] == resEdge.v0() && edg[1] == resEdge.v1()) || (edg[1] == resEdge.v0() && edg[0] == resEdge.v1())){
                return edg;
            }
        }
        return null;
    }

    private Edge dfs(int v, Map<Integer, List<Integer>> adjMap, Map<Integer, Integer> visitedToSteps, int prev, List<Edge> edges,Map<Edge, Integer> edgMap){
        if (visitedToSteps.containsKey(v)){
            int cycleStartStep = visitedToSteps.get(v);
            Edge resEdge = edges.get(cycleStartStep);
            for(int step = cycleStartStep + 1; step < edges.size(); step++){
                var currEdge = edges.get(step);
                if (edgMap.get(currEdge) > edgMap.get(resEdge)){
                    resEdge = currEdge;
                }
            }
            return resEdge;
        }

        visitedToSteps.put(v, edges.size());

        for (var neig : adjMap.getOrDefault(v, new LinkedList<>())){
            if (neig == prev){
                continue;
            }
            edges.add(new Edge(neig, v));
            var edge = dfs(neig, adjMap, visitedToSteps, v, edges, edgMap);
            if (edge != null){
                return edge;
            }
            edges.remove(edges.size() - 1); // !!! important
        }
        return null;
    }

    record Edge(int v0, int v1){ }
}

class SolutionOptim {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (var edg : edges){
            adjMap.computeIfAbsent(edg[0], k -> new LinkedList<>()).add(edg[1]);
            adjMap.computeIfAbsent(edg[1], k -> new LinkedList<>()).add(edg[0]);
        }

        Set<Integer> cycleNodes = dfs(1, adjMap, new HashSet<Integer>(), 0, new ArrayList<Integer>());
        for (var i = edges.length - 1; i >=0; i--){
            if (cycleNodes.contains(edges[i][0]) && cycleNodes.contains(edges[i][1])){
                return edges[i];
            }
        }
        return null;
    }

    private Set<Integer> dfs(int v, Map<Integer, List<Integer>> adjMap, Set<Integer> visited, int prev, List<Integer> path){
        if (visited.contains(v)){ // cycle detected
            Set<Integer> cycleNodes = new HashSet<>();
            int i = 0;
            for (; i < path.size(); i++) {
                if (path.get(i) == v) { // rewind to the cycle start node
                    break;
                }
            }
            for (; i < path.size(); i++) {
                cycleNodes.add(path.get(i)); // add all cycle nodes
            }
            return cycleNodes;
        }

        visited.add(v);
        path.add(v);

        for (var neig : adjMap.getOrDefault(v, new LinkedList<>())){
            if (neig == prev){
                continue;
            }
            var nodes = dfs(neig, adjMap, visited, v, path);
            if (nodes != null){
                return nodes;
            }
        }

        path.remove(path.size() - 1); // !!! - backtrack the path
        return null;
    }
}