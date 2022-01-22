

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    private static final int R = 256;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int maxlen = 0;
        for (String ascii : asciis) {
            if (ascii.length() > maxlen) {
                maxlen = ascii.length();
            }
        }

        String[] copy = Arrays.copyOf(asciis, asciis.length);
        for (int i = maxlen - 1; i >= 0; i--) {
            sortHelperLSD(copy, i);
        }

        return copy;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        String[] sorted = new String[asciis.length];
        List<LinkedList<String>> count = new ArrayList<>(R);
        for (int i = 0; i < R; i++) {
            count.add(new LinkedList<>());
        }

        for (String ascii : asciis) {
            int i = calIndex(ascii, index);
            count.get(i).addLast(ascii);
        }
        int j = 0;
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i).size() != 0) {
                while (count.get(i).size() != 0) {
                    sorted[j++] = count.get(i).removeFirst();
                }
            }
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }
        return;
    }

    private static int calIndex(String ascii, int index) {
        if (index >= ascii.length()) {
            return 0;
        }
        return ascii.charAt(index);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = new String[]{"56", "112", "94", "4", "9", "82", "394", "80"};
        String[] res = RadixSort.sort(asciis);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }


}
