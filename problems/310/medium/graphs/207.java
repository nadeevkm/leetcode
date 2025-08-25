import java.util.*;


class SolutionMaps {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        for (int[] relation : prerequisites) {
            adjacencyMap.computeIfAbsent(relation[1], k -> new LinkedList<>())
                    .add(relation[0]);
        }

        Set<Integer> notVisited = new HashSet<>();
        for (int c = 0; c < numCourses; c++) {
            notVisited.add(c);
        }

        Set<Integer> visited = new HashSet<>();
        while (notVisited.size() != 0) {
            boolean cycle = dfsCycle(notVisited.iterator().next(), adjacencyMap, visited, new HashSet<>());
            if (cycle) {
                return false;
            }
            notVisited.removeAll(visited);
        }

        return true;
    }

    public boolean dfsCycle(int course, Map<Integer, List<Integer>> adjacencyMap, Set<Integer> visited, Set<Integer> path) {

        if (path.contains(course)) {
            return true;
        }

        if (visited.contains(course)) {
            return false;
        }

        visited.add(course);
        path.add(course);

        for (int ac : adjacencyMap.getOrDefault(course, Collections.emptyList())) {
            boolean cycle = dfsCycle(ac, adjacencyMap, visited, path);
            if (cycle) {
                return true;
            }
        }

        path.remove(course);
        return false;
    }
}

class SolutionMatrix {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[][] adjacencyMatrix = new int[numCourses][numCourses];

        for (int[] relation : prerequisites) {
            adjacencyMatrix[relation[1]][relation[0]] = 1;
        }

        Set<Integer> notVisited = new HashSet<>();
        for (int c = 0; c < numCourses; c++) {
            notVisited.add(c);
        }

        while (notVisited.size() != 0) {
            Set<Integer> visited = new HashSet<>();
            boolean cycle = dfsCycle(notVisited.iterator().next(), adjacencyMatrix, visited, new HashSet<>());
            if (cycle) {
                return false;
            }
            notVisited.removeAll(visited);
        }

        return true;
    }

    public boolean dfsCycle(int course, int[][] adjacencyMatrix, Set<Integer> visited, Set<Integer> path) {

        if (path.contains(course)) {
            return true;
        }

        if (visited.contains(course)) {
            return false;
        }

        visited.add(course);
        path.add(course);

        for (int ac = 0; ac < adjacencyMatrix[course].length; ac++) {
            if (adjacencyMatrix[course][ac] == 1) {
                boolean cycle = dfsCycle(ac, adjacencyMatrix, visited, path);
                if (cycle) {
                    return true;
                }
            }
        }

        path.remove(course);
        return false;
    }
}

class SolutionLatest {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        var adjMap = new HashMap<Integer, List<Integer>>();
        // [0, 1] i.e. 1 -> 0
        for (var pair : prerequisites) {
            adjMap.computeIfAbsent(pair[1], (k) -> new LinkedList<Integer>())
                    .add(pair[0]);
        }

        var visited = new HashSet<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (dfsCycle(i, adjMap, visited, new HashSet<Integer>())) {
                return false;
            }
        }

        return true;
    }

    private boolean dfsCycle(int v, Map<Integer, List<Integer>> adjMap, Set<Integer> visited, Set<Integer> path) {
        if (path.contains(v)) {
            // found a cycle
            return true;
        }
        if (visited.contains(v)) {
            return false;
        }

        path.add(v);
        visited.add(v);

        for (var neighb : adjMap.getOrDefault(v, Collections.emptyList())) {
            if (dfsCycle(neighb, adjMap, visited, path)) {
                return true;
            }
        }

        path.remove(v); // !!! important to (clean)backtrack path
        return false;
    }
}