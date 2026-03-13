#include <bits/stdc++.h>
using namespace std;

class TrieNode
{
public:
    char data;
    vector<TrieNode *> children;
    bool isTerminal;
    TrieNode(char ch)
    {
        data = ch;
        children.resize(26, nullptr);
        isTerminal = false;
    }
};

class Trie
{
public:
    TrieNode *root;
    Trie()
    {
        root = new TrieNode(' ');
    }

    void insertUtil(string &word, int idx, int &len, TrieNode *node)
    {
        if (idx == len)
        {
            node->isTerminal = true;
            return;
        }

        char ch = word.at(idx);
        int i = ch - 'a';
        if (node->children[i] == nullptr)
        {
            node->children[i] = new TrieNode(ch);
            insertUtil(word, idx + 1, len, node->children[i]);
        }
        else
        {
            insertUtil(word, idx + 1, len, node->children[i]);
        }
    }

    void insert(string word)
    {
        int length = word.size();
        insertUtil(word, 0, length, root);
    }

    bool searchUtil(string &word, int idx, int &len, TrieNode *node)
    {
        if (idx == len)
        {
            return node->isTerminal;
        }
        char ch = word.at(idx);
        int i = ch - 'a';
        if(node->children[i] != nullptr)
        {
            return searchUtil(word,idx+1,len,node->children[i]);
        }
        else
        {
            return false;
        }
    }

    bool search(string word)
    {
        int len = word.size();
        return searchUtil(word, 0, len, root);
    }

    bool startsWithUtil(string &word, int idx, int &len, TrieNode *node)
    {
        if (idx == len)
        {
            return true;
        }
        char ch = word.at(idx);
        int i = ch - 'a';
        if(node->children[i] != nullptr)
        {
            return startsWithUtil(word,idx+1,len,node->children[i]);
        }
        else
        {
            return false;
        }
    }

    bool startsWith(string prefix)
    {
        int len = prefix.size();
        return startsWithUtil(prefix,0,len,root);
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */