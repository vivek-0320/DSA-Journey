#include <bits/stdc++.h>
using namespace std;

bool helper(vector<int> &unique, vector<int> &window)
{
    for (int i = 0; i < 26; i++)
    {
        if (window[i] < unique[i])
            return false;
    }
    return true;
};

pair<int, int> minWindow(string s)
{
    vector<int> unique_chars(26, 0);
    for (char ch : s)
    {
        if (unique_chars[ch - 'a'] == 0)
            unique_chars[ch - 'a'] = 1;
    }

    vector<int> window(26, 0);
    int left = 0;
    int minLen = s.length();
    int start = 0;

    for (int right = 0; right < s.length(); right++)
    {
        window[s[right] - 'a']++;
        while (helper(unique_chars, window))
        {
            if (right - left + 1 < minLen)
            {
                minLen = right - left + 1;
                start = left; // save left BEFORE shrinking
            }
            window[s[left] - 'a']--;
            left++;
        }
    }
    return {left, minLen};
}

int main()
{
    string s = "aabcbcdbca";
    auto pr = minWindow(s);
    cout << pr.first << " , " << pr.second << endl;
    return 0;
}