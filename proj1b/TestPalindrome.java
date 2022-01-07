import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String test1 = "a";
        String test2 = "aaaaac";
        String test3 = "abaaba";
        String test4 = "abcba";

        assertEquals(true, palindrome.isPalindrome(test1));
        assertEquals(false, palindrome.isPalindrome(test2));
        assertEquals(true, palindrome.isPalindrome(test3));
        assertEquals(true, palindrome.isPalindrome(test4));
    }
}
