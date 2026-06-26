# Definition for a binary tree node.
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        self.count = 0
        # 0 - Need Camera
        # 1 - Has Camera
        # 2 - Covered
        def helper(node):
            if not node:
                return 2
            
            l_state = helper(node.left)
            r_state = helper(node.right)
            
            if l_state == 0 or r_state == 0:
                self.count += 1
                return 1
            
            if l_state == 2 and r_state == 2:
                return 0
            
            if l_state == 1 or r_state == 1:
                return 2
            
            return 2   
        state = helper(root)
        if state == 0:
            self.count += 1 
        return self.count