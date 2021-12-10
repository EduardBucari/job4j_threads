package ru.job4j.synchronization;

/**
 * Синхронизация общих ресурсов.
 * Задание:
 * Данный код содержит ошибку атомарности.
 * Необходимо поправьте код и устранить ошибку.
 * (статический метод (static) заменяем на synchronized)
 */
public final class Cache {
    private static Cache cache;

    public synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
