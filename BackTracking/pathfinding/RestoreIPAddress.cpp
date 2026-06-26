#include <bits/stdc++.h>
using namespace std;

void backtrack(vector<string> &ans, string &path, int index, string s, int part)
{
    if (index == s.size() && part == 4)
    {
        ans.push_back(path);
        return;
    }
    if (part < 4)
    {
        for (int i = index; i < index + 3 && i < s.size(); i++)
        {
            if (i > index && s[index] == '0')
                break;
            int ip = atoi(s.substr(index, i - index + 1).c_str());
            //cout << "index , i , ip , part : " << index << " , " << i << " , " << ip << " , " << part << endl;
            if (ip >= 0 && ip <= 255)
            {
                string temp = path;
                if (part < 3)
                    path = path + to_string(ip) + ".";
                else
                    path = path + to_string(ip);
                //cout << path << endl;
                backtrack(ans, path, i + 1, s, part + 1);
                path = temp;
            } 
            else {
                break;
            }
        }
    }
}

vector<string> restoreIpAddresses(string s)
{
    vector<string> ans;
    string path = "";
    backtrack(ans, path, 0, s, 0);
    return ans;
}

int main()
{
    string s;
    cin >> s;
    vector<string> ans = restoreIpAddresses(s);
    for (auto str : ans)
        cout << str << endl;
    return 0;
}