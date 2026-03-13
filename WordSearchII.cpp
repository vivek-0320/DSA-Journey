#include <bits/stdc++.h>
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

class Solution
{
public:
    TrieNode *root;
    vector<int> dx = {1, -1, 0, 0};
    vector<int> dy = {0, 0, 1, -1};

    void addWord(string &word, TrieNode *root)
    {
        TrieNode *curr = root;
        for (char ch : word)
        {
            int idx = ch - 'a';
            if (curr->children[idx] == nullptr)
                curr->children[idx] = new TrieNode();

            curr = curr->children[idx];
        }
        curr->isTerminal = true;
    }

    void dfs(vector<string> &result, string &path, int x, int y, vector<vector<char>> &board, TrieNode *node)
    {
        char ch = board[x][y];

        // Failure Base Case: Dead end in Trie OR already visited cell
        if (ch == '#' || node->children[ch - 'a'] == nullptr)
        {
            return;
        }

        // Step INTO the valid child node
        TrieNode *nextNode = node->children[ch - 'a'];
        path += ch;

        // Success Base Case: Word Found
        if (nextNode->isTerminal)
        {
            result.push_back(path);
            nextNode->isTerminal = false; // Prevent duplicate entries
        }

        // Mark as visited and branch out
        board[x][y] = '#';
        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && ny >= 0 && nx < board.size() && ny < board[0].size())
            {
                dfs(result, path, nx, ny, board, nextNode);
            }
        }

        // Backtrack
        board[x][y] = ch;
        path.pop_back();
    }
    vector<string> findWords(vector<vector<char>> &board, vector<string> &words)
    {
        root = new TrieNode();
        for (string word : words)
            addWord(word, root);

        vector<string> result;
        string path = "";
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
            {
                dfs(result, path, i, j, board, root);
            }
        }
        return result;
    }
};

int main()
{
    return 0;
}