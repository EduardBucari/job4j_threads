package ru.job4j.wait;

/**
 * Обеспечить остановку потребителя.
 * В этом задании нужно разработать механизм остановки потребителя,
 * когда производитель закончил свою работу.
 * Задание:
 * Изменить код, так, что бы потребитель завершал свою работу. Код нужно изменить в методе main.
 * (т.е. нужно сделать так чтобы не было того что нить производитель закончила работу,
 * а нить потребитель продолжает ждать событий).
 */
public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(1);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
