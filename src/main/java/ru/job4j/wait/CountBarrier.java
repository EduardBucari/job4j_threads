package ru.job4j.wait;

/**
 * Управление нитью через wait.
 * Задание:
 * Разработайте класс, который блокирует выполнение по условию счетчика.
 */
public class CountBarrier {
    private final Object monitor = this;

    /**
     * Переменная total содержит количество вызовов метода count().
     */
    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Метод count изменяет состояние программы.
     * Это значит, что внутри метода count нужно вызывать метод notifyAll.
     */
    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    /**
     * Нити, которые выполняют метод await, могут начать работу если поле count >= total.
     * Если оно не равно, то нужно перевести нить в состояние wait.
     * Здесь нужно использовать цикл while для проверки состояния, а не оператор if.
     */
    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        Thread first = new Thread(
                () -> {
                    while (countBarrier.count <= countBarrier.total) {
                        countBarrier.count();
                        System.out.println("First");
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println("Second");
                }
        );
        first.start();
        second.start();
    }
}
