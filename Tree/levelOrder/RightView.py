from typing import Optional,List

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        result = []
        def helper(node,depth):
            if not node:
                return
            
            if depth == len(result):
                result.append(node.val)
                
            helper(node.left,depth+1)
            helper(node.right,depth+1)       
        helper(root, 0)
        return result
    