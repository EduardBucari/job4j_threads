package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddTest() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("CNN");
        assertTrue(cache.add(base));
    }

    @Test
    public void whenUpdateTest() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("CNN");
        Base newBase = new Base(1, 1);
        newBase.setName("BBC");
        cache.add(base);
        assertTrue((cache.update(newBase)));
        assertEquals(cache.get(1).getName(), "BBC");
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateAnotherVersion() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("CNN");
        Base newBase = new Base(1, 2);
        newBase.setName("BBC");
        cache.add(base);
        cache.update(newBase);
    }

    @Test
    public void whenDeleteTest() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("CNN");
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(1));
    }
}