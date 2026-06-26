from typing import Optional,List
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        self.paths = 0
        self.map = {0: 1}  # Base case

        def dfs(node, current_sum):
            if not node:
                return
            # 1. Update Current Sum
            current_sum += node.val
            # 2. The Math Check (Corrected Order)
            # "Do I have a prefix I can chop off to get the target?"
            old_sum_needed = current_sum - targetSum
            # 3. Lookup (Safe read)
            self.paths += self.map.get(old_sum_needed, 0)
            # 4. Update Map (Unconditional!)
            # Record this path so children can see it
            self.map[current_sum] = self.map.get(current_sum, 0) + 1
            # 5. Recurse
            dfs(node.left, current_sum)
            dfs(node.right, current_sum)
            # 6. Backtrack / Undo (Clean the state)
            self.map[current_sum] -= 1

        dfs(root, 0)
        return self.paths
            
            