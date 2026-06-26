class Node:
    def __init__(self,val):
        self.val = val
        self.left = None
        self.right = None
        
class BinaryTree:
    def __init__(self,root_key=None):
        self.root = Node(root_key) if root_key else None
        
    def insert(self,key):
        if not self.root:
            self.root = Node(key)
            return
        
        queue = [self.root]
        while queue:
            temp = queue.pop(0)
            
            if not temp.left:
                temp.left = Node(key)
                break
            else:
                queue.append(temp.left)
                
            if not temp.right:
                temp.right = Node(key)
                break
            else:
                queue.append(temp.right)
            
    def pre_order(self,current_node):
        if current_node:
            print(f"{current_node.val}", end=" -> ")
            self.pre_order(current_node.left)
            self.pre_order(current_node.right)
        
    def in_order(self,current_node):
        if current_node:
            self.in_order(current_node.left)
            print(f"{current_node.val}", end=" -> ")
            self.in_order(current_node.right)
            
    def post_order(self,current_node):
        if current_node:
            self.post_order(current_node.left)
            self.post_order(current_node.right)
            print(f"{current_node.val}", end=" -> ")
            
    def sum(self,current_node):
        if not current_node:
            return 0
        lh = 0
        rh = 0
        if current_node.left:
            lh = self.sum(current_node.left)
        if current_node.right:
            rh = self.sum(current_node.right)
        
        return lh+rh+current_node.val
            
if __name__ == "__main__":
    tree = BinaryTree()     
    tree.insert(1)
    tree.insert(2)
    tree.insert(3)
    tree.insert(4)
    tree.insert(5)
    tree.in_order(tree.root)
    print("")
    tree.pre_order(tree.root)
    print("")
    tree.post_order(tree.root)
    print("")
    print(tree.sum(tree.root))