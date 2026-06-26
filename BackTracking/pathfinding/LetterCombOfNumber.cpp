#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<string> key = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    void backtrack(vector<string> &result,string path,string digits,int i)
    {
        if(path.size() == digits.size())
        {
            result.push_back(path);
            return;
        }

        int dig = digits.at(i) - '0';
        for(char ch:key[dig])
        {
            path.push_back(ch);
            backtrack(result,path,digits,i+1);
            path.pop_back();
        }
    }
    
    vector<string> letterCombinations(string digits) {
        vector<string> result;
        backtrack(result,"",digits,0);
        return result;
    }
};

int main()
{
    string n;
    cin >> n;
    Solution ob;
    vector<string> ans = ob.letterCombinations(n);
    for (auto str : ans)
        cout << str << endl;
    return 0;
}