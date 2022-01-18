package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] arr = new int[M];
        int index = 0;
        for (Oomage oomage : oomages) {
            index = (oomage.hashCode() & 0x7FFFFFFF) % M;
            arr[index]++;
        }
        int size = oomages.size();
        int maxSize = (int) (size / 2.5);
        int minSize = (int) (size / 50);
        for (int i = 0; i < M; i++) {
            if (arr[i] > maxSize || arr[i] < minSize) {
                return false;
            }

        }
        return true;
    }
}
