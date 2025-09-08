import java.util.*;

import static java.util.Arrays.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, List<Object[]>> adjacencyMap = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {

            var relation = equations.get(i);

            var p1 = relation.get(0);
            var p2 = relation.get(1);
            double val = values[i];

            adjacencyMap.computeIfAbsent(p1, k -> new LinkedList<>())
                    .add(new Object[]{p2, val});

            adjacencyMap.computeIfAbsent(p2, k -> new LinkedList<>())
                    .add(new Object[]{p1, 1 / val});
        }

        double[] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            var relation = queries.get(i);
            var find = bfs(relation.get(0), relation.get(1), adjacencyMap);
            res[i] = find == null ? -1.0 : find;
        }

        return res;
    }

    public Double bfs(String p1, String p2, Map<String, List<Object[]>> adjacencyMap) {

        if (!adjacencyMap.containsKey(p1) || !adjacencyMap.containsKey(p2)) {
            return null;
        }

        Set<String> marked = new HashSet<>();
        Queue<Object[]> q = new LinkedList<>();
        q.add(new Object[]{p1, 1.0});
        marked.add(p1);

        while (!q.isEmpty()) {
            var node = q.poll();
            var nodeName = (String) node[0];
            var nodeDivisionPath = (double) node[1];

            if (nodeName.equals(p2)) {
                adjacencyMap.get(p1).add(new Object[]{p2, nodeDivisionPath});
                adjacencyMap.get(p2).add(new Object[]{p1, 1 / nodeDivisionPath});
                return nodeDivisionPath;
            }

            for (var neighbor : adjacencyMap.getOrDefault(nodeName, Collections.emptyList())) {
                var neighName = (String) neighbor[0];
                var neighDivisionPath = (double) neighbor[1];

                if (marked.contains(neighName)) {
                    continue;
                }

                q.add(new Object[]{neighName, nodeDivisionPath * neighDivisionPath});
                marked.add(neighName);
            }
        }

        return null;
    }
}



class SolutionRepetit {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Object[]>> adjMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            var eq = equations.get(i);
            var val = values[i];
            adjMap.computeIfAbsent(eq.get(0), k -> new LinkedList<Object[]>())
                    .add(new Object[]{eq.get(1), val});
            adjMap.computeIfAbsent(eq.get(1), k -> new LinkedList<Object[]>())
                    .add(new Object[]{eq.get(0), 1 / val});
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            var start = queries.get(i).get(0);
            var finish = queries.get(i).get(1);
            res[i] = adjMap.containsKey(start) && adjMap.containsKey(finish)
                    ? dfs(start, finish, adjMap, new HashSet<>(), 1.0)
                    : -1.0;
        }
        return res;
    }

    private double dfs(String curr, String finish, Map<String, List<Object[]>> adjMap, Set<String> visited, double path) {
        if (visited.contains(curr)) {
            return -1.0;
        }
        if (curr.equals(finish)) {
            return path;
        }
        visited.add(curr);
        for (var neighInfo : adjMap.getOrDefault(curr, Collections.emptyList())) {
            var neigh = (String) neighInfo[0];
            var weight = (double) neighInfo[1];
            var res = dfs(neigh, finish, adjMap, visited, path * weight);
            if (res != -1.0) {
                return res;
            }
        }
        return -1.0;
    }
}


class Check {

    public static void main(String[] args) {
        var equations = asList(asList("a", "b"), asList("c", "d"));
        var values = new double[]{1.0, 1.0};
        var queries = asList(asList("a", "c"), asList("b", "d"), asList("b", "a"), asList("d", "c"));

        new Solution().calcEquation(equations, values, queries);

        int p = 1;
    }
}