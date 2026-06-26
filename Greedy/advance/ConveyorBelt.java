import java.util.*;

public class ConveyorBelt {
    
    // A block represents a chunk of the conveyor belt we are forced to process together
    static class Block {
        long sum;
        int length;
        long[] arr; // Reference to the original belt (A or B)
        int start;  // Starting index of this block in the belt
        
        Block(long sum, int length, long[] arr, int start) {
            this.sum = sum;
            this.length = length;
            this.arr = arr;
            this.start = start;
        }
    }
    
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        
        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextLong();
        }
        
        // Step 1: Group trapped items into blocks for both belts
        List<Block> blocksA = getBlocks(a);
        List<Block> blocksB = getBlocks(b);
        
        // Step 2: Pool all blocks together
        List<Block> allBlocks = new ArrayList<>();
        allBlocks.addAll(blocksA);
        allBlocks.addAll(blocksB);
        
        // Step 3: Sort all blocks based on their average value in ascending order
        allBlocks.sort((b1, b2) -> {
            // Compare whole division first
            long w1 = b1.sum / b1.length;
            long w2 = b2.sum / b2.length;
            
            if (w1 != w2) {
                return Long.compare(w1, w2);
            }
            
            // If whole numbers match, safely cross-multiply the remainders 
            // This guarantees exact precision and prevents Long.MAX_VALUE overflow
            long r1 = b1.sum % b1.length;
            long r2 = b2.sum % b2.length;
            return Long.compare(r1 * b2.length, r2 * b1.length);
        });
        
        // Step 4: Unpack the blocks in sorted order to get the maximum score
        long totalScore = 0;
        long k = 1;
        
        for (Block block : allBlocks) {
            for (int i = 0; i < block.length; i++) {
                totalScore += block.arr[block.start + i] * k;
                k++;
            }
        }
        
        System.out.println(totalScore);
        scanner.close();
    }
    
    // Helper function that fuses "trapped" items into blocks
    private static List<Block> getBlocks(long[] arr) {
        List<Block> stack = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            Block curr = new Block(arr[i], 1, arr, i);
            
            // If the previous block's average is >= current block's average, fuse them!
            while (!stack.isEmpty()) {
                Block prev = stack.get(stack.size() - 1);
                
                long w1 = prev.sum / prev.length;
                long w2 = curr.sum / curr.length;
                
                boolean shouldMerge = false;
                if (w1 > w2) {
                    shouldMerge = true;
                } else if (w1 == w2) {
                    long r1 = prev.sum % prev.length;
                    long r2 = curr.sum % curr.length;
                    if (r1 * curr.length >= r2 * prev.length) {
                        shouldMerge = true;
                    }
                }
                
                if (shouldMerge) {
                    // Absorb current block into the previous one
                    curr.sum += prev.sum;
                    curr.length += prev.length;
                    curr.start = prev.start; // Merged block starts where the previous one did
                    stack.remove(stack.size() - 1);
                } else {
                    break;
                }
            }
            stack.add(curr);
        }
        return stack;
    }
}
