# Definition for a binary tree node.
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if not root:
            return
        
        def getTail(node) -> TreeNode:
            if not node.right:
                return node
            return getTail(node.right)
        
        if root.left : self.flatten(root.left)
        if root.right: self.flatten(root.right)
        
        if root.left :
            tail = getTail(root.left)
            tail.right = root.right
            root.right = root.left
            root.left = None
            
    def __init__(self) -> None:
        self.prev = None
        
    def flatten_optimzed(self,root: Optional[TreeNode]) -> None:
        """
        This approach uses Reverse Engineering concept.
        Instead of building the chain from front , we find last block then attach to last second block and so on.
        So Reverse Post Order is used. Right -> Left -> Root
        and A pointer to the last block processes is maintained to not compute everytime.
        """
        if not root:
            return
        
        self.flatten_optimzed(root.right)
        self.flatten_optimzed(root.left)
        
        root.right = self.prev
        root.left = None
        self.prev = root
        
        
            
            
            
            
        