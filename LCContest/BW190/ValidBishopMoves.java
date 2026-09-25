class ValidBishopMoves {

    public int minBishopMoves(int[] source, int[] target) {
        // diff colors
        if ((source[0] + source[1]) % 2 != (target[0] + target[1]) % 2)
            return -1;

        // same diagonal
        if (Math.abs(source[0] - target[0]) == Math.abs(source[1] - target[1]))
            return 1;

        // Same color, different diagonal
        return 2;
    }
}