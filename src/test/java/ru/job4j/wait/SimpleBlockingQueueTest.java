package ru.job4j.wait;


import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void whenLogicSimpleBlocking() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(() -> {
            try {
                smb.offer(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(
                () -> {
                    try {
                        smb.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }


    @Test
    public void whenLogicSimpleBlockingTwo() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(8);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 8) {
                        i++;
                        try {
                            smb.offer(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    try {
                        smb.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void whenLogicBlockingThree() throws InterruptedException {
        SimpleBlockingQueue<Integer> smb = new SimpleBlockingQueue<>(5);
        Thread first = new Thread(
                () -> {
                    int i = 0;
                    while (i < 7) {
                        i++;
                        try {
                            smb.offer(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                   int i = 0;
                   while (i < 6) {
                       i++;
                       try {
                           smb.poll();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}