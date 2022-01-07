package com.bunny.cs61b_sp18.proj1gold;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

/**
 * @author Bunny
 * @create 2022-01-07 14:47
 */
public class TestArrayDequeGold {

    static ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();

    @Test
    public void testPrintDeque() {
        for (int i = 0; i < 10; i++) {
            int uniform = StdRandom.uniform(50);
            if (uniform % 2 == 0) {
                arrayDequeSolution.addFirst(uniform);
            } else {
                arrayDequeSolution.addLast(uniform);
            }
        }
        arrayDequeSolution.printDeque();
        arrayDequeSolution.removeFirst();
        arrayDequeSolution.printDeque();;
        arrayDequeSolution.removeLast();
        arrayDequeSolution.printDeque();

    }
}
