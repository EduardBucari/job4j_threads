package ru.job4j.concurrent;

/**
 * Состояние нити.
 * Задание:
 * 1. Поправьте класс ThreadState там образом, чтобы в нем создавалось две нити.
 *    Каждая нить должна вывести свое имя на консоль.
 *
 * 2. Нить main должна дождаться завершения этих нитей и вывести на консоль сообщение, что работа завершена.
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();

        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println("First: " + first.getName()
                    + ".  Second: " + second.getName());
        }
        System.out.println("The process is finish");
    }
}
