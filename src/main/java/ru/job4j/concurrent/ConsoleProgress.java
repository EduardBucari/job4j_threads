package ru.job4j.concurrent;

/**
 * Прерывание нити.
 * В классе Thread есть метод interrupt().
 * Этот метод выставляет флаг прерывания, но никаких дополнительных действий не совершает.
 * Задание:
 * 1. Создайте класс ru.job4j.concurrent.ConsoleProgress.
 *    Этот класс будет использован для вывода процесса загрузки в консоль:
 *         Thread progress = new Thread(new ConsoleProgress());
 *         progress.start();
 *         Thread.sleep(1000); (симулируем выполнение параллельной задачи в течение 1 секунды.)
 *         progress.interrupt();
 * 2. Этот класс должен реализовывать интерфейс java.lang.Runnable.
 * 3. Внутри метода run нужно добавить цикл с проверкой флага. Внутри цикла - сделать задержку в 500 мс.
 * 4. В тело цикла добавьте вывод в консоль.
 *    Loading ... |.
 *    Последний символ должен меняться: - \ | /.
 *    System.out.print("\r load: " + process[...]);
 *    Эти символы создадут эффект крутящегося шара.
 * 5. Добавьте в этот класс метод main с демонстрацией работы этого класса.
 */
public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }


    /**
     * Прерывание блокированной нити.
     * Задание:
     * Поправьте код в классе ru.job4j.concurrent.ConsoleProgress.
     * В блоке catch уберите исключение [e.printStackTrace();]
     * и добавьте прерывание [Thread.currentThread().interrupt();]
     */
    @Override
    public void run() {
        String[] process = {"\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {

            try {
                for (String s : process) {
                    Thread.sleep(500);
                    System.out.print("\r load: " + s);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
