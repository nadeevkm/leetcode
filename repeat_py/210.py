from collections import deque
from typing import List


class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        outdegree = {}
        indegree = {}

        for pair in prerequisites:
            outs = outdegree.setdefault(pair[1], set())
            outs.add(pair[0])
            ins = indegree.setdefault(pair[0], set())
            ins.add(pair[1])

        q = deque()
        for course in range(numCourses):
            if course not in indegree:
                q.append(course)

        res = []
        while q:
            course = q.popleft()
            res.append(course)
            for nextCourse in outdegree.get(course, set()):
                indegree[nextCourse].remove(course)
                if len(indegree[nextCourse]) == 0:
                    indegree.pop(nextCourse)
                    q.append(nextCourse)

        return res if len(res) == numCourses else []


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

