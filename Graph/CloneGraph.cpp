#include<bits/stdc++.h>
using namespace std;


class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

class Solution {
public:
    Node* cloneGraph(Node* node) {
        if (node == nullptr) return node;

        // If already cloned, return the clone
        if (visited.contains(node) )  {
            return visited[node];
        }

        // Create clone of current node
        Node* clone = new Node(node->val, vector<Node*>{});
        visited[node] = clone;

        // Clone neighbors
        for (auto neighbor : node->neighbors) {
            clone->neighbors.push_back(cloneGraph(neighbor));
        }
        return clone;
    }
private:
unordered_map<Node*, Node*> visited;
};

int main()
{
    return 0;
}