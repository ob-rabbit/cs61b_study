package com.bunny.cs61b_sp18.proj1b;

/**
 * @author Bunny
 * @create 2022-01-07 13:14
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
