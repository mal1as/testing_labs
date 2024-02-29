package com.itmo.tpo.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    @DisplayName("Basic sorting test")
    public void basicTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {3, 2, 4, 1, 7, 6, 5};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Already sorted array test")
    public void sortedArrayTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {1, 2, 3, 4, 5, 6, 7};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Reversed array test")
    public void reversedArrayTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {7, 6, 5, 4, 3, 2, 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Empty array test")
    public void emptyArrayTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {};
        Integer[] expected = {};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Negative array values test")
    public void negativeValuesTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {-4, -2, -6, -1, -3, -5};
        Integer[] expected = {-6, -5, -4, -3, -2, -1};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Check same array values test")
    public void sameValuesTest() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {1, 1, 1, 1};
        Integer[] expected = {1, 1, 1, 1};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    @DisplayName("Custom class test")
    public void customClassTest() {
        HeapSort<TestEntity> heapSort = new HeapSort<>();
        TestEntity apple = new TestEntity("apple");
        TestEntity banana = new TestEntity("banana");
        TestEntity grape = new TestEntity("grape");
        TestEntity pear = new TestEntity("pear");
        TestEntity[] input = {banana, pear, grape, apple};
        TestEntity[] expected = {apple, banana, grape, pear};
        heapSort.sort(input);
        assertArrayEquals(expected, input);
    }

    private static class TestEntity implements Comparable<TestEntity> {

        private final String name;

        public TestEntity(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(TestEntity o) {
            return this.name.compareTo(o.name);
        }
    }
}