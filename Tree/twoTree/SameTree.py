# Definition for a binary tree node.
from collections import deque
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def isSameTree(self, p, q) -> bool:
        queue = deque([(p,q)])
        while queue:
            nodeA, nodeB = queue.popleft()
            if not nodeA and not nodeB:
                continue
            if not nodeA or not nodeB or nodeA.val != nodeB.val:
                return False
            queue.append((nodeA.left, nodeB.left))
            queue.append((nodeB.right, nodeB.right))
        return True
    
    def isSameTree2(self, p, q) -> bool:
        if not p and not q:
            return True
        if not p or not q or p.val != q.val:
            return False
        
        return self.isSameTree2(p.left,q.left) and self.isSameTree2(p.right,q.right)
        
       
        