from typing import List
from collections import deque
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        vis = [0] * n  # 0: uncolored, 1: blue, -1: red
        deq = deque()
        
        for i in range(n):
            if vis[i] == 0:
                vis[i] = 1
                deq.append(i)
                
                while deq:
                    u = deq.popleft()
                    
                    for v in graph[u]:
                        # Conflict check
                        if vis[v] == vis[u]: 
                            return False
                        
                        # Unvisited neighbor
                        elif vis[v] == 0:
                            vis[v] = -vis[u]  # Math trick to flip color
                            deq.append(v)
                            
        return True
            
        
        
        