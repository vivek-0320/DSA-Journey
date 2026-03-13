#include <string>
using namespace std;

class TrieNode
{
public:
    TrieNode *children[26];
    bool isTerminal;

    TrieNode()
    {
        isTerminal = false;
        for (int i = 0; i < 26; i++)
            children[i] = nullptr;
    }
};

class WordDictionary
{
public:
    TrieNode *root;

    WordDictionary()
    {
        root = new TrieNode();
    }

    void addWord(string word)
    {
        TrieNode *curr = root;
        for (char ch : word)
        {
            if (curr->children[ch - 'a'] == nullptr)
            {
                curr->children[ch - 'a'] = new TrieNode();
            }
            curr = curr->children[ch - 'a'];
        }
        curr->isTerminal = true;
    }

    bool searchUtil(string &word, int idx, TrieNode *node)
    {
        if (idx == word.size())
            return node->isTerminal;

        char ch = word.at(idx);
        if (ch == '.')
        {
            for (int i = 0; i < 26; i++)
            {
                if (node->children[i] != nullptr)
                {
                    if (searchUtil(word, idx + 1, node->children[i]))
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        else
        {
            if (node->children[ch - 'a'] == nullptr)
                return false;

            return searchUtil(word, idx + 1, node->children[ch - 'a']);
        }
    }

    bool search(string word)
    {
        TrieNode *curr = root;
        return searchUtil(word, 0, root);
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */