package com.bunny.cs61b_sp18.proj1gold;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

/**
 * @author Bunny
 * @create 2022-01-07 14:59
 */
public class TestStudentArrayDeque {

    static StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();

    static {
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                studentArrayDeque.addLast(i);
            } else {
                studentArrayDeque.addFirst(i);
            }
        }
    }
    @Test
    public void testAddFirst(){
        studentArrayDeque.printDeque();
        studentArrayDeque.addFirst(123);
        studentArrayDeque.printDeque();
    }

    @Test
    public void testAddLast(){
        studentArrayDeque.printDeque();
        studentArrayDeque.addLast(123);
        studentArrayDeque.printDeque();
    }

    @Test
    public void testRemoveFirst(){
        studentArrayDeque.printDeque();
        studentArrayDeque.removeFirst();
        studentArrayDeque.printDeque();
    }

    @Test
    public void testRemoveLast(){
        studentArrayDeque.printDeque();
        studentArrayDeque.removeLast();
        studentArrayDeque.printDeque();
    }



}
