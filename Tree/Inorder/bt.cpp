#include<bits/stdc++.h>
using namespace std;

class node
{
    public:
    string val;
    node *left;
    node *right;

    node(string val)
    {
        this->val=val;
        left=nullptr;
        right=nullptr;
    }
};

class BinaryTree
{
public:
    node *root;
    BinaryTree()
    {
        this->root=nullptr;
    }

    void preOrder(node *root)
    {
        if(root==nullptr) return;
        cout << root->val << " ";
        preOrder(root->left);
        preOrder(root->right);
    }

    void preorderIterative()
    {
        stack<node*> st;
        st.push(this->root);
        while(!st.empty())
        {
            node* top=st.top();
            cout << top->val << " ";
            st.pop();
            if(top->right) st.push(top->right);
            if(top->left) st.push(top->left);
        }
    }

    void inOrder(node *root)
    {
        if(root==nullptr) return;
        inOrder(root->left);
        cout << root->val << " ";
        inOrder(root->right);
    }

    void inOrderIterative()
    {
        stack<node*> st;
        node* top=this->root;
        while(top!=nullptr || !st.empty())
        {
            while(top!=nullptr)
            {
                st.push(top);
                top=top->left;
            }
            top=st.top();
            cout << top->val << " ";
            st.pop();
            top=top->right;                
        }
    }

    
    void postOrder(node *root)
    {
        if(root==nullptr) return;
        postOrder(root->left);
        postOrder(root->right);
        cout << root->val << " ";        
    }

    void postOrderIterative2()
    {
       stack<node*> st1,st2;
       st1.push(this->root);
       while(!st1.empty())
       {
            node* top=st1.top();
            st1.pop();
            if(top->left) st1.push(top->left);
            if(top->right) st1.push(top->right);
            st2.push(top);
       }
       while (!st2.empty())
       {
            cout << (st2.top())->val << " ";
            st2.pop();
       }       
    }

    void postOrderIterative1()
    {
        stack<node*> st;
        node* curr=root;
        node* temp=nullptr;
        while(curr!=nullptr || !st.empty())
        {
            if(curr!=nullptr)
            {
                st.push(curr);
                curr=curr->left;
            }            
            else
            {
                temp=st.top()->right;
                if(temp==nullptr)
                {

                    temp=st.top();
                    st.pop();
                    cout << temp->val << " ";
                    while(!st.empty() && temp==st.top()->right)
                    {
                        temp=st.top();
                        st.pop();
                        cout << temp->val << " ";
                    }
                }
                else
                {
                    curr=temp;
                }
            }
        }
    }

    void postOrderIterative3()
    {
        stack<node*> st;
        stack<bool> visit;
        st.push(this->root);
        visit.push(false);
        node* curr=nullptr;
        bool v=false;
        while(!st.empty())
        {
            curr=st.top(); st.pop();
            v=visit.top(); visit.pop();
            if(curr)
            {
                if(v)
                {
                    cout << curr->val << " ";
                }
                else
                {
                    st.push(curr); visit.push(true);
                    st.push(curr->right); visit.push(false);
                    st.push(curr->left); visit.push(false);
                }
            }            
        }
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

    bool search(string val)
    {
        queue<node*> q;
        q.push(root);
        while(!q.empty())
        {
            int l=q.size();
            node* curr=q.front(); q.pop();
            if(curr->val == val)
            {
                return true;
            }
            if(curr->left) q.push(curr->left);
            if(curr->right) q.push(curr->right);
        }
        return false;
    }

    void insert(string val)
    {
        if (root == nullptr) {
            root = new node(val);
            return;
        }
        queue<node*> q;
        q.push(root);
        while(!q.empty())
        {
            node* curr = q.front(); q.pop();
            if(curr->left==NULL)
            {
                curr->left=new node(val);
                return;             
            }
            else if(curr->right==NULL)
            {
                curr->right=new node(val);
                return;  
            }
            else
            {
                if(curr->left) q.push(curr->left);
                if(curr->right) q.push(curr->right);
            }            
        }
    }


};

int main()
{
    BinaryTree bt= BinaryTree();
    bt.insert("A");
    bt.insert("B");
    bt.insert("C");
    bt.insert("D");
    bt.insert("E");
    bt.insert("F");
    bt .levelOrder();
    bt.insert("G");
    bt.levelOrder();
}