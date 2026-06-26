class Solution:
    def maxProduct(self, root) -> int:
        MOD = 10**9 + 7
        max_result = 0
        
        def dfs(node):
            nonlocal max_result
            if not node:
                return 0
            left = dfs(node.left)
            right = dfs(node.right)

            subtree_sum = left + right + node.val
            product = subtree_sum * (total_sum - subtree_sum)

            max_result = max(max_result, product)
            return subtree_sum

        # First pass: total tree sum
        def sumTree(node):
            if not node:
                return 0
            return node.val + sumTree(node.left) + sumTree(node.right)

        total_sum = sumTree(root)
        dfs(root)

        return max_result % MOD
