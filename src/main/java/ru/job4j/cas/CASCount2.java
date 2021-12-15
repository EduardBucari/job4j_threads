package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS - операции.
 * Задание:
 * 1. Реализовать неблокирующий счетчик.
 * 2. Для решения нужно использовать CAS операции.
 * 3. Написать тесты.
 * (Чтобы сделать Stack потокобезопасным мы будем использовать класс:
 * java.util.concurrent.atomic.AtomicReference)
 */
@ThreadSafe
public class CASCount2 {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount2(int value) {
        this.count.set(value);
    }

    public void increment() {
        int value;
        int nextValue;
        do {
            value = count.get();
            nextValue = value + 1;
        } while (!count.compareAndSet(value, nextValue));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASCount2 casCount2 = new CASCount2(2);
        casCount2.increment();
        System.out.println(casCount2.get());
        casCount2.increment();
        System.out.println(casCount2.get());
        casCount2.increment();
        System.out.println(casCount2.get());
        casCount2.increment();
        System.out.println(casCount2.get());
    }
}
