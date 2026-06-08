#include <bits/stdc++.h>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
    int rob(TreeNode *root)
    {
        vector<int> finalState = dfs(root);
        return max(finalState[0],finalState[1]); // [ skip , take ]
    }

    vector<int> dfs(TreeNode  *node)
    {
        if(nullptr == node)
            return {0,0};
        
        vector<int> leftState = dfs(node->left);
        vector<int> rightState = dfs(node->right);

        int skip = max(leftState[0],leftState[1]) + max(rightState[0],rightState[1]);
        int take = node->val + leftState[0] + rightState[0];

        return {skip,take};
    }
};

int main()
{
    return 0;
}