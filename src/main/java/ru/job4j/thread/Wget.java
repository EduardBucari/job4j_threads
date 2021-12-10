package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Скачивание файла с ограничением.
 * Задание:
 * 1. Написать консольную программу - аналог wget.
 * 2. Программа должна скачивать файл из сети с ограничением по скорости скачки.
 *    Чтобы ограничить скорость скачивания, нужно засечь время скачивания 1024 байт.
 *    Если время меньше указанного, то нужно выставить паузу за счет Thread.sleep.
 *    Пауза должна вычисляться, а не быть константой.
 */
public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                int time = speed - bytesRead;
                if (time < 0) {
                    time = speed + bytesRead;
                }
                Thread.sleep(time);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}

