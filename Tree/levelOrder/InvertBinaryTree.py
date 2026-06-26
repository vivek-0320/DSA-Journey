from collections import deque
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def invertTree(self, root: TreeNode) -> TreeNode:
        if not root:
            return root
        queue = deque([root])
        while queue:
            n = len(queue)
            for _ in range(n):
                top = queue.popleft()
                temp = top.left or None
                top.left = top.right or None
                top.right = temp
                
                if top.left:
                    queue.append(top.left)
                if top.right:
                    queue.append(top.right)
        return root
        
        