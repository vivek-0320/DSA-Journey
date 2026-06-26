# Definition for a binary tree node.
from typing import Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        if not root: 
            return 0
        self.max_sum = root.val
        def dfs(node):
            if not node:
                return 0
            
            l_gain = max(dfs(node.left),0)
            r_gain = max(dfs(node.right),0)
            
            current_split_path = l_gain + r_gain + node.val
            self.max_sum = max(self.max_sum, current_split_path)
            
            return node.val + max(l_gain,r_gain)
        dfs(root)
        return self.max_sum
        
        
            