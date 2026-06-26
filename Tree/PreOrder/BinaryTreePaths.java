import java.util.*;
//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    List<String> result;
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        dfs(root,path);
        return result;
    }

    void dfs(TreeNode node, List<Integer> path) {
        if(node == null)
            return;
        path.add(node.val);
        if(node.left == null && node.right == null){
            result.add(processPath(path));
        }
        dfs(node.left,path);
        dfs(node.right,path);
        path.remove(path.size() - 1);
    }

    String processPath(List<Integer> path) {
        StringBuilder str = new StringBuilder();
        for(int i=0;i<path.size();i++) {
            str.append(String.valueOf(path.get(i)));
            if(i< path.size() - 1)
                str.append("->");
        }
        return str.toString();
    }
}