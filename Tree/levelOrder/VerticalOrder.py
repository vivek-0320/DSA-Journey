# Definition for a binary tree node.
from typing import Optional,List
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        self.bucket = {}        
        def dfs(node,x,y):
            if not node:
                return
            
            if y not in self.bucket:
                self.bucket[y] = []  # Create a new empty list for this key
            self.bucket[y].append((x,node.val)) # Now it is safe to append
                
            dfs(node.left,x+1,y-1)
            dfs(node.right,x+1,y+1)
        dfs(root,0,0)
        res = []
        
        for key in sorted(self.bucket):
            temp = self.bucket[key]
            sorted_tuple = sorted(temp)
            res.append([item[1] for item in sorted_tuple])
        return res
