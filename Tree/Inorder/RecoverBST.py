# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def recoverTree(self, root) -> None:
        self.list = []
        def inorder(node):
            if not node:
                return 
            inorder(node.left)
            self.list.append(node.val)                    
            inorder(node.right)
        inorder(root)
        x = -1
        y = -1
        pr = 0
        cnt = 0
        for i in range(1,len(self.list)):
            if self.list[pr] > self.list[i]:
                cnt += 1
                if cnt == 1:
                    x = pr
                y = i
            pr += 1
        t = self.list[x]
        self.list[x] = self.list[y]
        self.list[y] = t
        self.i = 0
        def modifyTree(node):
            if not node:
                return
            modifyTree(node.left)
            node.val = self.list[self.i]
            self.i += 1
            modifyTree(node.right)
        modifyTree(root)
        
    def optimized(self,root):
        self.prev = float("-inf")
        self.a = None
        self.b = None
        self.count = 0
        self.prevNode = None
        def inorder(node):
            if not node:
                return
            
            inorder(node.left)
            
            if self.prev > node.val:
                self.count += 1
                if self.count == 1:
                    self.a = self.prevNode
                self.b = node
                
            self.prevNode = node
            self.prev = node.val
            
            inorder(node.right)
        inorder(root)
        t = self.a.val
        self.a.val = self.b.val
        self.b.val = t
            
            
            
            
        
                    
                
        