package ru.job4j.asynchron;

import java.util.concurrent.CompletableFuture;

/**
 * Класс содержит две реализации для подсчёта суммы чисел в ряду и столбце.
 */
public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    /**
     * Метод sum() последовательно подсчитывает сумму чисел в ряду и столбце.
     * @param matrix матрица.
     * @return массив, содержащий сумму числе в i ряду и столбце.
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = count(matrix, i);
        }
        return sums;
    }

    /**
     * Метод asyncSum() асинхронно подсчитывает сумму чисел в ряду и столбце.
     * @param matrix матрица.
     * @return массив, содержащий сумму чисел в i ряду и столбце.
     * @throws Exception exception.
     */
    public static Sums[] asyncSum(int[][] matrix) throws Exception {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTask(matrix, i).get();
        }
        return sums;
    }

    /**
     * Метод getTask() запускает асинхронную задачу.
     * @param matrix матрица.
     * @param start с какого ряда и столбца начать подсчёт суммы.
     * @return объект CompletableFuture.
     */
    public static CompletableFuture<Sums> getTask(int[][] matrix, int start) {
        return CompletableFuture.supplyAsync(() -> count(matrix, start));
    }

    /**
     * Метод count() описывает алгоритм подсчёта суммы в матрице.
     * @param matrix матрица.
     * @param start с какого ряда и столбца начать подсчёт суммы.
     * @return модель данных Sums, в которой храниться сумма чисел в ряду и столбце.
     */
    private static Sums count(int[][] matrix, int start) {
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSum += matrix[start][i];
            colSum += matrix[i][start];
        }
        return new Sums(rowSum, colSum);
    }
}
