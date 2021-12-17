package ru.job4j.pools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchIndexTest {

    @Test
    public void whenSearchElement30Test() {
        int[] array = {29, 97, 43, 49, 47, 30, 11, 20, 98, 54, 76};
        var rsl = ParallelSearchIndex.search(array, 30);
        assertEquals(rsl.intValue(), 5);
    }

    @Test
    public void whenNoValueInArrayTest() {
        int[] array = {29, 97, 43, 49, 47, 30, 11, 20, 98, 54, 76};
        var rsl = ParallelSearchIndex.search(array, 77);
        assertEquals(rsl.intValue(), -1);
    }

    @Test
    public void whenSearchElement29Test() {
        int[] array = {29, 97, 43, 49, 47, 30, 11, 20, 98, 54, 76};
        var rsl = ParallelSearchIndex.search(array, 29);
        assertEquals(rsl.intValue(), 0);
    }
}