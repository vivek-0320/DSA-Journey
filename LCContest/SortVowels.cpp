#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>

using namespace std;

class Solution {
public:
    string sortVowels(string s) {
        unordered_map<char, int> freq;
        unordered_map<char, int> firstPos;
        string vowelsOnly = "";
        
        // Define vowels for quick lookup
        string vowelList = "aeiou";
        auto isVowel = [&](char c) {
            return vowelList.find(c) != string::npos;
        };

        // Stage 1: Gather metrics and extract vowels
        for (int i = 0; i < s.length(); ++i) {
            if (isVowel(s[i])) {
                if (freq.find(s[i]) == freq.end()) {
                    firstPos[s[i]] = i;
                }
                freq[s[i]]++;
                vowelsOnly += s[i];
            }
        }

        // Stage 2: Sort based on the two-tier criteria
        sort(vowelsOnly.begin(), vowelsOnly.end(), [&](char a, char b) {
            if (freq[a] != freq[b]) {
                // Higher frequency comes first
                return freq[a] > freq[b];
            }
            // If frequency is same, earlier first-occurrence comes first
            return firstPos[a] < firstPos[b];
        });

        // Stage 3: Re-insert into the original string
        int vIdx = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (isVowel(s[i])) {
                s[i] = vowelsOnly[vIdx++];
            }
        }

        return s;
    }
};