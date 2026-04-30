#include <bits/stdc++.h>
using namespace std;

class TrieNode
{
public:
    bool isEnd;
    TrieNode *next[26];

    TrieNode()
    {
        this->isEnd = false;
        for (int i = 0; i < 26; i++)
        {
            this->next[i] = nullptr; // Corrected: Use = instead of ==
        }
    }

    // Destructor to prevent memory leaks
    ~TrieNode()
    {
        for (int i = 0; i < 26; i++)
        {
            if (next[i])
                delete next[i];
        }
    }
};

class Trie
{
    TrieNode *root;

    // Helper function for DFS to find up to 3 words with a given prefix
    void dfs(TrieNode *curr, string &word, vector<string> &result)
    {
        if (result.size() == 3)
            return;
        if (curr->isEnd)
            result.push_back(word);

        for (int i = 0; i < 26; i++)
        {
            if (curr->next[i])
            {
                word += (char)('a' + i);
                dfs(curr->next[i], word, result);
                word.pop_back(); // Backtrack
            }
        }
    }

public:
    Trie()
    {
        root = new TrieNode();
    }

    ~Trie()
    {
        delete root;
    }

    void insertWord(const string &s)
    {
        TrieNode *t = root;
        for (char ch : s)
        {
            int idx = ch - 'a';
            // Corrected: Only create a new node if it doesn't exist
            if (t->next[idx] == nullptr)
            {
                t->next[idx] = new TrieNode();
            }
            t = t->next[idx];
        }
        t->isEnd = true;
    }

    vector<string> getSuggestions(string &prefix)
    {
        TrieNode *t = root;
        vector<string> result;

        // 1. Navigate to the end of the prefix
        for (char ch : prefix)
        {
            int idx = ch - 'a';
            if (t->next[idx] == nullptr)
                return result;
            t = t->next[idx];
        }

        // 2. Perform DFS from that node to find top 3 words
        dfs(t, prefix, result);
        return result;
    }
};

vector<vector<string>> suggestedProducts(vector<string> &products, string searchWord)
{
    Trie trie;
    // Optimization: Sorting ensures we find lexicographical order during DFS
    sort(products.begin(), products.end());

    for (const string &s : products)
    {
        trie.insertWord(s);
    }

    vector<vector<string>> finalResult;
    string currentPrefix = "";

    for (char ch : searchWord)
    {
        currentPrefix += ch;
        finalResult.push_back(trie.getSuggestions(currentPrefix));
    }

    return finalResult;
}

int main()
{
    return 0;
}