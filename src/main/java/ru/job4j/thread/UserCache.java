package ru.job4j.thread;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Thread без общих ресурсов.
 * Задание:
 * 1. Программист решил расширить класс UserCache и добавил в него новый метод findAll.
 * 2. Исправьте ошибку в этом коде.
 *
 * (Для решения этой задачи, дополнительно создадим модель данных User.)
 */
@ThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        /*
        return new ArrayList<>(users.values());
         */
        List<User> rsl = new ArrayList<>();
        for (User user : users.values()) {
            rsl.add(User.of(user.getName()));
        }
        return rsl;
    }
}
