package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool.
 * Задание:
 * Реализовать параллельный поиск индекса в массиве объектов.
 */
public class ParallelSearchIndex extends RecursiveTask<Integer> {

    private final int[] array;
    private final int index;
    private final int from;
    private final int to;

    public ParallelSearchIndex(int[] array, int index, int from, int to) {
        this.array = array;
        this.index = index;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            for (int i = from; i <= to; i++) {
                if (array[i] == index) {
                    return i;
                }
            }
            return -1;
        }
        int middle = (from + to) / 2;
        ParallelSearchIndex left = new ParallelSearchIndex(array, index, from, middle);
        ParallelSearchIndex right = new ParallelSearchIndex(array, index, middle + 1, to);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static Integer search(int[] array, int index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex(array, index, 0, array.length - 1));
    }
}
