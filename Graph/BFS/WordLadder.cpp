#include <bits/stdc++.h>
using namespace std;

int ladderLength(string beginWord, string endWord, vector<string> &wordList)
{
    // Check if endWord is even in the list
    unordered_set<string> dict(wordList.begin(), wordList.end());
    if (dict.find(endWord) == dict.end())
        return 0;

    unordered_map<string, vector<string>> patternToWord;
    for (const string &word : wordList)
    {
        string pattern = word;
        for (int i = 0; i < pattern.size(); i++)
        {
            char original = pattern[i];
            pattern[i] = '*';
            patternToWord[pattern].push_back(word);
            pattern[i] = original;
        }
    }
    queue<pair<string, int>> q;
    unordered_set<string> visited;

    q.push({beginWord, 1});
    visited.insert(beginWord);

    while (!q.empty())
    {
        auto [current_word, step] = q.front();
        q.pop();
        if (current_word == endWord)
            return step;

        string pattern = current_word;
        for (int i = 0; i < pattern.size(); i++)
        {
            char original = pattern[i];
            pattern[i] = '*';

            for (string nei : patternToWord[pattern])
            {
                if (visited.count(nei) == 0)
                {
                    q.push({nei, step + 1});
                    visited.insert(nei);
                }
            }
            pattern[i] = original;
        }
    }
    return 0;
}

int main()
{
    string beginWord = "hit";
    string endWord = "cog";
    vector<string> wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
    cout << ladderLength(beginWord, endWord, wordList) << endl;

    return 0;
}