from typing import List

def partitionLabels(s: str) -> None:
    last = {}
    for i,ch in enumerate(s):
            last[ch] = i
    start = 0
    end = 0
    result = []
    for i,ch in enumerate(s):
        end = max(end,last[ch])
        if(i==end):
            result.append(end - start + 1)
            start = end+1
    print(result)


partitionLabels("ababcbacadefegdehijhklij")
