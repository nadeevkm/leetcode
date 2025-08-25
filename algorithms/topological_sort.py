# Can be done via DFS and stack or BFS and indegree table


# In BFS we compose AdjMap and IndegreeTable (table with a count of incoming edges for each vertex)
# and layer by layer remove all vertex with indegree == 0, adding them to the resulted topological sort.




# BFS
class SolutionIndegreeAsAList:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        adjMap = {}
        indegree = [0]*numCourses

        for pair in prerequisites:
            adjMap.setdefault(pair[1], set()).add(pair[0])
            indegree[pair[0]] += 1

        q = deque()
        for course in range(numCourses):
            if indegree[course] == 0:
                q.append(course)

        res = []
        while q:
            course = q.popleft()
            res.append(course)
            for nextCourse in adjMap.get(course, set()):
                indegree[nextCourse] -= 1
                if indegree[nextCourse] == 0:
                    q.append(nextCourse)

        return res if len(res) == numCourses else []