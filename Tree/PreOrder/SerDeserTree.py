# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
from collections import deque

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        """
        if not root:
            return ""
        
        # Use a Queue for BFS
        queue = deque([root])
        res = []
        
        while queue:
            node = queue.popleft()
            
            if node:
                # 1. Add value to list
                res.append(str(node.val))
                # 2. Add children (even if they are None) to queue
                queue.append(node.left)
                queue.append(node.right)
            else:
                # Handle Null
                res.append("N")
        
        # Join with comma to handle multi-digit numbers (e.g., "10,20,N,N")
        return ",".join(res)

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        """
        if not data:
            return None
        
        # Split string by delimiter to get values
        vals = data.split(",")
        root = TreeNode(int(vals[0]))
        queue = deque([root])
        
        # Use an index pointer to consume the values list
        i = 1 
        
        while queue:
            node = queue.popleft()
            
            # Process Left Child
            if vals[i] != "N":
                node.left = TreeNode(int(vals[i]))
                queue.append(node.left)
            i += 1
            
            # Process Right Child
            if vals[i] != "N":
                node.right = TreeNode(int(vals[i]))
                queue.append(node.right)
            i += 1
            
        return root

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))