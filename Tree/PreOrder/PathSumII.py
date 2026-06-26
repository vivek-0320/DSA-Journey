# Definition for a binary tree node.
from typing import List,Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        self.result = []
        def dfs(node,path,target,currentSum):
            if not node:
                return
            path.append(node.val) 
            if (currentSum+node.val) == target and not node.left and not node.right:
                self.result.append(path[:])
                path.pop()
                return
            
            dfs(node.left,path,target,currentSum+node.val)
            dfs(node.right,path,target,currentSum+node.val)  
            path.pop()
                   
            
        dfs(root,[],targetSum,0)       
        return self.result