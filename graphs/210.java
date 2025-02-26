import java.util.*;

class Solution {  // BFS topologySort, adjacenyMap + indices list,
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indices = new int[numCourses];
        for (int i = 0; i < numCourses; i++){
            adjList.add(new LinkedList<>());
        }
        for (var pr : prerequisites){
            int to = pr[0];
            int from = pr[1];
            adjList.get(from).add(to);
            indices[to]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int c = 0; c < numCourses; c++){
            if (indices[c] == 0){
                q.offer(c);
            }
        }

        int[] res = new int[numCourses];
        int resInd = 0;
        while (!q.isEmpty()){
            int course = q.poll();
            res[resInd] = course;
            resInd++;

            for (int to : adjList.get(course)){
                indices[to]--;
                if (indices[to] == 0){
                    q.offer(to);
                }
            }
        }

        return resInd == numCourses ? res : new int[]{};
    }
}

class SolutionDFS {  // DFS topologySort, only adjacencyMap
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for (int[] pr : prerequisites) {
            adjMap.computeIfAbsent(pr[1], k -> new LinkedList<>())
                    .add(pr[0]);
        }

        BitSet visited = new BitSet(numCourses);
        BitSet path = new BitSet(numCourses);
        Deque<Integer> sortStack = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited.get(i)) {
                var order = topSort(i, adjMap, visited, sortStack, path);
                if (order == null) {
                    return new int[]{};
                }
            }
        }

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = sortStack.pop();
        }
        return res;
    }

    public Deque<Integer> topSort(int course, Map<Integer, List<Integer>> adjMap, BitSet visited, Deque<Integer> order, BitSet path) {
        if (path.get(course)) {
            return null;
        }

        if (visited.get(course)) {
            return order;
        }

        visited.set(course);
        path.set(course);

        for (int neigh : adjMap.getOrDefault(course, new LinkedList<>())) {
            var neigOrder = topSort(neigh, adjMap, visited, order, path);
            if (neigOrder == null) {
                return null;
            }
        }

        path.clear(course);

        order.push(course);

        return order;
    }
}
