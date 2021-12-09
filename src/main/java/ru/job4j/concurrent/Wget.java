package ru.job4j.concurrent;

/**
 * Режим ожидания.
 * Задание:
 * 1. Создайте класс ru.job4j.concurrent.Wget и метод main.
 *
 * 2. В методе main необходимо симулировать процесс загрузки.
 *    Для этого воспользуйтесь особенностью вывода на консоль:
 *    System.out.print("\rLoading : " + index  + "%");
 *    - Метод print печатает символы в строку без перевода каретки.
 *    - Символ \r указывает, что каретку каждый раз нужно вернуть в начало строки.
 *    Это позволяет через промежуток времени обновить строчку.
 *
 * 3. Создайте нить внутри метода main. В теле метода создайте цикл от 0 до 100.
 *    Через 1 секунду выводите на консоль информацию о загрузке.
 *    Вывод должен быть с обновлением строки.
 */
public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            System.out.println("\rLoading : " + i  + "%");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        first.start();
    }
}
