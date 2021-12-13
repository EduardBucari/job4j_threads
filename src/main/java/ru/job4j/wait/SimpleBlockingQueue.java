package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализовать шаблон Producer Consumer.
 * Это блокирующая очередь, ограниченная по размеру.
 * В данном шаблоне Producer помещает данные в очередь, а Consumer извлекает данные из очереди.
 * Ваша задача используя, wait/notify реализовать блокирующую очередь.
 * Задание:
 * 1. Реализовать методы poll() и offer().
 * 2. Написать тесты. В тестах должны быть две нити: одна производитель, другая потребитель.
 *    Через методы join() добиться последовательного выполнение программы.
 */
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final int count;

    public SimpleBlockingQueue(Integer count) {
        this.count = count;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == count) {
            wait();
        }
        queue.add(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        notify();
        return queue.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(7);

        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        try {
                            smb.offer(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Добавлено");
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        try {
                            smb.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Получено");
                    }
                }
        );
        second.start();
        first.start();
        first.join();
        second.join();
    }
}
