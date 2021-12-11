package ru.job4j.thread;

/**
 * Модель памяти Java.
 * Задание:
 * 1. Ниже приведен код синглтона - double check locking.
 * 2. Исправьте в нем ошибку.
 * (необходимо добавить облегченный механизм синхронизации - volatile.
 * volatile - это ключевое слово, которое используется для полей класса.
 * Если поле класса обозначено volatile,
 * то чтение и запись переменной будет происходить
 * только из RAM памяти процессора.)
 *
 */
public class DCLSingleton {

    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

}
