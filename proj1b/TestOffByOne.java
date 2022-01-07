import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        char a = 'a';
        char b = 'b';
        char c = 'a';
        char d = 'A';
        assertEquals(false, offByOne.equalChars(a, c));
        assertEquals(true, offByOne.equalChars(a, b));
        assertEquals(false, offByOne.equalChars(a, d));
    }
}
