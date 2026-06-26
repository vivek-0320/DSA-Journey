from typing import List
from collections import deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        indegree = [0]*numCourses
        adjList = [ [] for _ in range(numCourses)]
        for pair in prerequisites:
            v = pair[0]
            u = pair[1]
            adjList[u].append(v)
            indegree[v] += 1

        deq = deque()
        for i,u in enumerate(indegree):
            if u==0:
                deq.append(i)
        while deq:
            curr = deq.popleft()
            for v in adjList[curr]:
                indegree[v] -= 1
                if indegree[v] == 0:
                    deq.append(v)
        for u in indegree:
            if u != 0:
                return False
        return True
            
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        indegree = [0]*numCourses
        adjList = [ [] for _ in range(numCourses)]
        for pair in prerequisites:
            v = pair[0]
            u = pair[1]
            adjList[u].append(v)
            indegree[v] += 1

        res = []
        deq = deque()
        for i,u in enumerate(indegree):
            if u==0:
                deq.append(i)
                res.append(i)
        while deq:
            curr = deq.popleft()
            for v in adjList[curr]:
                indegree[v] -= 1
                if indegree[v] == 0:
                    deq.append(v)
                    res.append(v)
        for u in indegree:
            if u != 0:
                return []
        return res