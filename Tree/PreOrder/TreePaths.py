from typing import Optional,List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        result = []
        def dfs(node,path: List):
            if  not node:
                return
            
            path.append(str(node.val)) #Choose
            
            if not node.left and not node.right:
                result.append("->".join(path))
            
            dfs(node.left,path) #Explore left
            dfs(node.right,path) #Explore right
            
            path.pop() #Undo
        dfs(root,[])
        print(result)
        return result

            