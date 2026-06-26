
class Solution:
    def helper(self,node):
        if not node:
            return 0, True
        
        lh,ls = self.helper(node.left)
        rh,rs = self.helper(node.right)
        
        if not ls or not rs or abs(lh-rh) > 1:
            return 0,False
        
        return max(lh,rh)+1,True
        
    def check(self,node):
        _,state = self.helper(node)
        return state