def solve():
    a, b, c = map(int, input().split(" "))
    print(max(abs(a-b),(a+c-b)))


t = int(input())
while t != 0:
    solve()
    t -= 1
