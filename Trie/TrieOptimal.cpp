#include <string>

class TrieNode
{
public:
    TrieNode *children[26];
    bool isTerminal;

    TrieNode()
    {
        isTerminal = false;
        // Raw array initialization is highly cache-friendly
        for (int i = 0; i < 26; i++)
        {
            children[i] = nullptr;
        }
    }
};

class Trie
{
private:
    TrieNode *root;

    // Helper to recursively free memory
    void clear(TrieNode *node)
    {
        for (int i = 0; i < 26; i++)
        {
            if (node->children[i] != nullptr)
            {
                clear(node->children[i]);
            }
        }
        delete node;
    }

public:
    Trie()
    {
        root = new TrieNode();
    }

    // Crucial for preventing memory leaks in robust C++ applications
    ~Trie()
    {
        clear(root);
    }

    void insert(const std::string &word)
    {
        TrieNode *curr = root;
        for (char ch : word)
        {
            int idx = ch - 'a';
            if (curr->children[idx] == nullptr)
            {
                curr->children[idx] = new TrieNode();
            }
            curr = curr->children[idx];
        }
        curr->isTerminal = true;
    }

    bool search(const std::string &word)
    {
        TrieNode *curr = root;
        for (char ch : word)
        {
            int idx = ch - 'a';
            if (curr->children[idx] == nullptr)
            {
                return false;
            }
            curr = curr->children[idx];
        }
        return curr->isTerminal;
    }

    bool startsWith(const std::string &prefix)
    {
        TrieNode *curr = root;
        for (char ch : prefix)
        {
            int idx = ch - 'a';
            if (curr->children[idx] == nullptr)
            {
                return false;
            }
            curr = curr->children[idx];
        }
        return true;
    }
};
