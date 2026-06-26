from typing import Optional,List
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        deq = deque([(root,0)])
        max_width = 0
        while deq:
            
            n = len(deq)
            minIndex = deq[0][1]
            first_index, last_index = 0, 0
            
            for i in range(n):
                curr,idx = deq.popleft()
                idx = idx - minIndex
                if i == 0 : first_index = idx
                if i == n-1 : last_index = idx
                
                if curr.left : deq.append((curr.left,(2*idx)))
                if curr.right : deq.append((curr.right,(2*idx+1)))
                max_width = max(max_width,(last_index - first_index + 1))
                
        return max_width
                
            