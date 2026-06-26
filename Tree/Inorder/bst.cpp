#include<bits/stdc++.h>
using namespace std;

class node
{
    public:
    int val;
    node *left;
    node *right;

    node(int val)
    {
        this->val=val;
        left=nullptr;
        right=nullptr;
    }
};

class bst
{
private:
    node* root;

    node* insert(node* root,int val)
    {
        if(root == nullptr)
        {
            return new node(val);
        }
        else if(val < root->val)
        {
            root->left =  insert(root->left,val);
        }
        else
        {
            root->right =  insert(root->right,val);
        }
        return root;
    }

    bool search(node* root,int val)
    {
        if(root == nullptr)         return false;

        if(root->val == val)        return true;
        else if( val < root->val)   return search(root->left,val);
        else                        return search(root->right,val);

    }

    int insc(node* root)
    {
        node* t=root;
        while(t->left!=nullptr)
            t=t->left;

        return t->val;
    }

    void deletenode(node* root,int del)
    {
        if(root==nullptr)  return;
        node* prev=nullptr;
        node* t=root;
        while(t!=nullptr)
        {
            if(t->val == del)
            {
                break;
            }
            else if(del < t->val)
            {
                prev=t;
                t=t->left;
            }            
            else
            {
                prev=t;
                t=t->right;
            }            
        }
        if(t!=nullptr)
        {
            if(t->left!=nullptr && t->right!=nullptr)
            {
                int is=insc(t->right);
                deletenode(t,is);
                t->val=is;

            }
            else if(t->left==nullptr&&t->right!=nullptr)
            {
                if(prev==nullptr)
                    root=root->right;
                else{
                    prev->right=t->right;
                }                
            }
            else if(t->left!=nullptr&&t->right==nullptr)
            {
                if(prev==nullptr)
                    root=root->left;
                else{
                    prev->left=t->left;
                }          
            }
            else
            {
                if(prev==nullptr)
                {
                    root=nullptr;
                }
                else
                {
                        if(prev->left==t) prev->left=nullptr;
                    else    prev->right=nullptr;
                }           
            }
        }
    }
public:

    bst()
    {
        this->root=nullptr;
    }

    void insert(int val)
    {
        root = insert(root,val);
    }

    void search(int val)
    {
        if(search(root,val))
        cout << "YES\n";
        else
        cout << "NO\n";
    }

    int largest()
    {
        node* t=root;
        while(t->right!=nullptr)
            t=t->right;

        return t->val;
    }

    int smallest()
    {
        node* t=root;
        while(t->left!=nullptr)
            t=t->left;

        return t->val;
    }

    void deletenode(int val)
    {
        deletenode(root,val);
    }

    void levelOrder()
    {
        queue<node*> q;
        q.push(this->root);
        while(!q.empty())
        {
            int len=q.size();
            for(int i=0;i<len;i++)
            {
                node* front=q.front(); q.pop();
                cout << front->val << " ";
                if(front->left) q.push(front->left);
                if(front->right) q.push(front->right);
            }
            cout << endl;
        }
    }

};

int main()
{
    bst bt= bst();
    bt.insert(5);
    bt.insert(10);
    bt.insert(15);
    bt.insert(2);
    bt.levelOrder();
    cout << "largest: " << bt.largest() << endl;
    cout << "smallest: " << bt.smallest() << endl; 
    bt.deletenode(5);
    bt.levelOrder();
    cout << "largest: " << bt.largest() << endl;
    cout << "smallest: " << bt.smallest() << endl; 
    bt.deletenode(15);
    bt.levelOrder();
    cout << "largest: " << bt.largest() << endl;
    cout << "smallest: " << bt.smallest() << endl; 

}