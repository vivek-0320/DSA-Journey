from collections import deque
from typing import Optional,List
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        path1 = []
        path2 = []
        def dfs(node, current_path):
            if not node:
                return
            if not node.left and not node.right:
                current_path.append(node.val)
                
            dfs(node.left, current_path)
            dfs(node.right, current_path)
        dfs(root1, path1)
        dfs(root2, path2)
        return path1 == path2
        
            
            