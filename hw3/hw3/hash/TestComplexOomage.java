package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }


    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        int N = 10000;

        for (int i = 0; i < N; i++) {
            deadlyList.add(randomC());
        }


        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    private ComplexOomage randomC() {
        int size = StdRandom.uniform(1, 10);
        ArrayList<Integer> params = new ArrayList<>(size);
        int num = StdRandom.uniform(9, 13);
        for (int i = 0; i < size; i += 1) {
            params.add(num);
        }
        return new ComplexOomage(params);
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
