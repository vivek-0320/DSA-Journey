from typing import List,Set
from collections import deque

def checkIfPrerequisite(self, numCourses: int, prerequisites: List[List[int]], queries: List[List[int]]) -> List[bool]:
    adjList = [ [] for _ in range(numCourses)]
    prereqs: List[Set[int]] = [set() for _ in range(numCourses)]
    inDegree = [0]*numCourses
    
    for edge in prerequisites:
        u, v = edge[0], edge[1]
        adjList[u].append(v)
        inDegree[v] += 1
        
    deq = deque()
    for i,v in enumerate(inDegree):
        if v==0:
            deq.append(i)
            
    while deq:
        curr = deq.popleft()
        for v in adjList[curr]:
            prereqs[v].update(prereqs[curr])
            prereqs[v].add(curr)
            
            inDegree[v] -= 1
            if inDegree[v] == 0:
                deq.append(v)
              
    result = []  
    for query in queries:
        u,v = query[0], query[1]
        condition = u in prereqs[v]
        result.append(condition)
    return result
        
                    
    
        