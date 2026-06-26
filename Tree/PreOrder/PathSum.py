# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def hasPathSum(self, root, targetSum: int):
        def dfs(node,targetSum,currentSum):
            if not node:
                return False
            
            currentSum += node.val
            if not node.left and not node.right:
                return currentSum == targetSum

            return dfs(node.left,targetSum,currentSum) or dfs(node.right,targetSum,currentSum)

        return dfs(root,targetSum,0)