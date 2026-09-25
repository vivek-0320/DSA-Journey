def solve():
    n = int(input())
    s = input()

    z = 0
    for ch in s:
        if ch == "0":
            z += 1

    if s[0] == "1":
        print(z)
        return

    c1 = 0
    c0 = 0

    minops = z

    for ch in s:
        if ch == "0":
            c0 += 1
        else:
            c1 += 1

        suff_0 = z - c0
        ops = c1 + suff_0
        minops = min(minops, ops)

    print(minops)


t = int(input())
while t != 0:
    solve()
    t -= 1
