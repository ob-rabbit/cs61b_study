package com.bunny.cs61b_sp18.proj1b;

/**
 * @author Bunny
 * @create 2022-01-07 13:13
 */
public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        char[] chars = word.toCharArray();
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(chars[i]);
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        char[] chars = word.toCharArray();
        int i = 0, j = word.length() - 1;
        while (i <= j && chars[i] == chars[j]) {
            i++;
            j--;
        }
        if (i > j) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || cc == null) {
            return false;
        }
        char[] chars = word.toCharArray();
        int i = 0, j = word.length() - 1;
        while (i < j && cc.equalChars(chars[i], chars[j])) {
            i++;
            j--;
        }
        if (i >= j) {
            return true;
        }
        return false;
    }
}
