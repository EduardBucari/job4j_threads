package ru.job4j.concurrent;

/**
 * Запуск нити. Thread#start()
 * Задание:
 * 1. В метод main класса ru.job4j.concurrent.ConcurrentOutput создайте еще один объект Thread.
 *    Присвойте имя переменной second.
 * 2. В конструкторе нового объекта задайте вывод на консоль имени новой нити.
 *    Для этого воспользуйтесь оператором Thread.currentThread().getName();
 * 3. Запустите нить на выполнение. Для этого вызовите у объекта метод Thread#start().
 * 4. Запустите метод main несколько раз и убедитесь,
 *    что последовательность вывода имен нитей всегда произвольная.
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
    }
}
