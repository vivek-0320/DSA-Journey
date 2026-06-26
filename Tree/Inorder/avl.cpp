#include <bits/stdc++.h>
using namespace std;

class TreeNode {
public:
    int val;
    int height;
    TreeNode *left;
    TreeNode *right;

    // Constructor to initialize node
    TreeNode(int value) {
        val = value;
        height = 1;
        left = right = nullptr;
    }
};

class AVLTree {
private:
    TreeNode* root;

    int height(TreeNode *root) {
        if (root == nullptr) return 0;
        return root->height;
    }

    int balance(TreeNode *root) {  // Fixed typo from 'balanace' to 'balance'
        if (root == nullptr) return 0;
        return height(root->left) - height(root->right);
    }

    TreeNode* rotateRight(TreeNode *disbalancedNode) {
        TreeNode* newRoot = disbalancedNode->left;
        TreeNode* T2 = newRoot->right;

        // Perform rotation
        newRoot->right = disbalancedNode;
        disbalancedNode->left = T2;

        disbalancedNode->height = 1 + max(height(disbalancedNode->left), height(disbalancedNode->right));
        newRoot->height = 1 + max(height(newRoot->left), height(newRoot->right));

        return newRoot;
    }

    TreeNode* rotateLeft(TreeNode *disbalancedNode) {
        
        TreeNode* newRoot = disbalancedNode->right;
        TreeNode* T2 = newRoot->left;

        // Perform rotation
        newRoot->left = disbalancedNode;
        disbalancedNode->right = T2;

        disbalancedNode->height = 1 + max(height(disbalancedNode->left), height(disbalancedNode->right));
        newRoot->height = 1 + max(height(newRoot->left), height(newRoot->right));

        return newRoot;
    }

    TreeNode* insert(TreeNode *root, int val) {
        if (root == nullptr) {
            return new TreeNode(val);
        } else if (val < root->val) {
            root->left = insert(root->left, val);
        } else {
            root->right = insert(root->right, val);
        }

        root->height = 1 + max(height(root->left), height(root->right));

        int bal = balance(root);

        // Left Left Case
        if (bal > 1 && val < root->left->val) {
            return rotateRight(root);
        }

        // Left Right Case
        if (bal > 1 && val > root->left->val) {
            root->left = rotateLeft(root->left);
            return rotateRight(root);
        }

        // Right Right Case
        if (bal < -1 && val > root->right->val) {
            return rotateLeft(root);
        }

        // Right Left Case
        if (bal < -1 && val < root->right->val) {
            root->right = rotateRight(root->right);
            return rotateLeft(root);
        }

        return root;
    }

    void levelOrder(TreeNode* node) {
        if (!node) {
            cout << "Tree is empty." << endl;
            return;
        }

        queue<TreeNode*> q;
        q.push(node);
        while (!q.empty()) 
        {
            int count = q.size();
            for (int i = 0; i < count; i++) 
            {
                TreeNode* current = q.front();
                q.pop();
                cout << current->val << " ";

                if (current->left) {
                    q.push(current->left);
                }
                if (current->right) {
                    q.push(current->right);
                }
            }
            cout << endl;
        }
    }

    void preOrder(TreeNode *root)
    {
        if(root==nullptr) return;
        cout << root->val << " ";
        preOrder(root->left);
        preOrder(root->right);
    }

public:

    AVLTree()
    {
        root=nullptr;
    }

    void insert(int val)
    {
        root = insert(root,val);
    }

    void displaypre()
    {
        preOrder(root);
    }

    void displaylevel()
    {
        levelOrder(root);
    }

};

int main() {
    AVLTree tree;
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(50);
    tree.insert(25);

    cout << "Level order traversal of the AVL tree:" << endl;
    tree.displaylevel();

    tree.displaypre();
    cout << endl;

    return 0;
}
