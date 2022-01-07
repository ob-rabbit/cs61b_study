

/**
 * @author Bunny
 * @create 2022-01-07 13:14
 */
public class OffByN implements CharacterComparator {

    private int offset;

    public OffByN(int offset) {
        offset = 5;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == this.offset;
    }
}
