from typing import List
from collections import deque

class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        n = len(graph)
        out = [0]*n
        deq = deque()
        revGraph =[[] for _ in range(n)]
        safeNodes = []
        
        for i,node in enumerate(graph):
            out[i] = len(node)
            if out[i] == 0:
                deq.append(i)
            for v in node:
                revGraph[v].append(i)           
                
        while deq:
            curr = deq.popleft()
            safeNodes.append(curr)
            
            for v in revGraph[curr]:
                out[v] -= 1
                if out[v] == 0:
                    deq.append(v)
            
        safeNodes.sort()       
        #print(safeNodes)
        return safeNodes
    
def main():
    graph = [[1,2],[2,3],[5],[0],[5],[],[]]
    ob = Solution()
    ob.eventualSafeNodes(graph)
    
if __name__ == "__main__":
    main()