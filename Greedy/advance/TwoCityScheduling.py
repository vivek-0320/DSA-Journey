from typing import List
def twoCitySchedCost(costs: List[List[int]]) -> int:
    cost = 0
    n = len(costs)
    costs.sort(key=lambda x: (-x[0],x[1]))
    print(costs)
    for i in range(int(n/2)):
        cost += costs[i][1]
    for i in range(int(n/2),n):
        cost += costs[i][0]
    return cost
    
x = twoCitySchedCost([[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]])
print(x)