
def solve():
    t = int(input())
    out = []
    idx = 1
    
    for _ in range(t):
        n = int(input())
        idx += 1
                
        # Step 1: Calculate the invariant magic values (a_i - i)
        c = []
        for i in range(n):
            a = int(input())
            # Using 1-based indexing
            c.append(a - (i + 1))
            
        idx += n
        
        # Step 2 & 3: Remove duplicates and sort the array
        c = sorted(list(set(c)))
        
        # Step 4: Find the longest contiguous chain of integers
        max_len = 1
        current_len = 1
        
        for i in range(1, len(c)):
            if c[i] == c[i-1] + 1:
                # The chain continues
                current_len += 1
                if current_len > max_len:
                    max_len = current_len
            else:
                # The chain broke, reset the counter
                current_len = 1
                
        out.append(str(max_len))
        
    # Print all results separated by a newline
    print('\n'.join(out))

if __name__ == '__main__':
    solve()