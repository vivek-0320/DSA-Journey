#include <bits/stdc++.h>
using namespace std;

// unions b into a
void unionSet(unordered_set<int> &a, unordered_set<int> &b)
{
    for (int num : b)
    {
        a.insert(num);
    }
}

void printPreReqs(const unordered_map<int, unordered_set<int>> &preReqs)
{
    if (preReqs.empty())
    {
        cout << "The prerequisite map is empty." << endl;
        return;
    }

    for (const auto &[course, prereqSet] : preReqs)
    {
        cout << "Course " << course << " Prereqs: { ";

        if (prereqSet.empty())
        {
            cout << "None ";
        }
        else
        {
            for (const auto &prereq : prereqSet)
            {
                cout << prereq << " ";
            }
        }

        cout << "}" << endl;
    }
}

vector<bool> checkIfPrerequisite(int numCourses, vector<vector<int>> &prerequisites, vector<vector<int>> &queries)
{
    vector<vector<int>> adjList(numCourses, vector<int>());
    vector<int> indegree(numCourses, 0);
    for (vector<int> row : prerequisites)
    {
        int u = row[0];
        int v = row[1];
        adjList[u].push_back(v);
        indegree[v]++;
    }
    deque<int> deq;
    for (int i = 0; i < numCourses; i++)
    {
        if (indegree[i] == 0)
            deq.push_back(i);
    }
    unordered_map<int, unordered_set<int>> preReqs(numCourses);
    while(!deq.empty())
    {
        int u = deq.front();
        deq.pop_front();
        for(int v : adjList[u])
        {
            indegree[v]--;
            preReqs[v].insert(u);
            unionSet(preReqs[v],preReqs[u]);
            if(indegree[v] == 0)
                deq.push_back(v);
        }
    }
    printPreReqs(preReqs);
    vector<bool> result;
    for (vector<int> query : queries)
    {
        int u = query[0];
        int v = query[1];
        if (preReqs[v].find(u) != preReqs[v].end())
            result.push_back(true);
        else
            result.push_back(false);
    }
    return result;
}

int main()
{

    return 0;
}