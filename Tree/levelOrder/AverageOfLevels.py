from collections import deque
from typing import Optional,List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        avgs = []
        deq = deque([root])
        while deq:
            n = len(deq)
            sum = 0
            for _ in range(n):
                node = deq.popleft()
                sum += node.val # pyright: ignore[reportOptionalMemberAccess]
                if node.left:   deq.append(node.left) # type: ignore
                if node.right:  deq.append(node.right) # pyright: ignore[reportOptionalMemberAccess]
            avgs.append(sum/n)
        return  avgs
                
        