from typing import List,Optional
from collections import deque

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder or not inorder:
            return None

        pos = {v: i for i, v in enumerate(inorder)}  # value -> index in inorder
        self.pre_i = 0

        def helper(in_left,in_right):
            if in_left > in_right:
                return None
            
            node = TreeNode(preorder[self.pre_i])
            self.pre_i += 1
            mid = pos[node.val]
            
            node.left = helper(in_left,mid-1)
            node.right = helper(mid+1, in_right)
            
            return node
        
        return helper(0,len(preorder) - 1)
        
def main():
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    ob = Solution()
    print(ob.buildTree(preorder,inorder))
    
if __name__ == "__main__":
    main()
     
        