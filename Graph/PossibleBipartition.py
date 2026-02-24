from typing import List
from collections import deque
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        adjList = [[] for _ in range(n+1)]
        for pair in dislikes:
            u = pair[0]
            v = pair[1]
            adjList[u].append(v)
            adjList[v].append(u)
        
        deq = deque()
        vis = [0]*n
        for i in range(1,n+1):
            if vis[i] == 0:
                vis[i] = 1
                deq.append(i)
                while deq:
                    u = deq.popleft()
                    for v in adjList[u]:
                        if vis[v] == vis[u]:
                            return False
                        elif vis[v] == 0:
                            vis[v] = -vis[u]
                            deq.append(v)
        return True