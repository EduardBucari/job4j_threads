package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс хранилища пользователей UserStorage.
 * Задние:
 * Это классическая задача по переводу денег с одного счета на другой.
 * Чтобы операции были атомарны, нам нужен один объект монитора.
 * В этом задании объект монитора будет объект класса UserStore.
 * 1. Создать класс - структуру данных для хранение пользователей UserStorage.
 *    Сделать блокирующий кеш UserStorage для модели данных User.
 * 2. В заголовке класса обозначить аннотацию @ThreadSafe из библиотеки jcip.
 * 3. Хранилище должно иметь методы: boolean add (User user),
 *    boolean update(User user), boolean delete(User user).
 * 4. И особый метод transfer(int fromId, int toId, int amount);
 * 5. Структура данных должна быть потокобезопасная;
 * 6. В структуре данных User должны быть поля int id, int amount.
 *    amount - это сумма денег на счете пользователя.
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        User first = users.get(fromId);
        User second = users.get(told);
        if (first != null && second != null
              && first.getAmount() >= amount) {
            first.setAmount(users.get(fromId).getAmount() - amount);
            second.setAmount(users.get(told).getAmount() + amount);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        User user1 = new User(1, 100);
        User user2 = new User(2, 50);
        UserStorage userStorage = new UserStorage();
        System.out.println(userStorage.add(user1));
        System.out.println(userStorage.add(user2));
        System.out.println(userStorage.transfer(1, 2, 50));
        System.out.println(userStorage.update(user1));
        System.out.println(userStorage.update(user2));
        System.out.println(userStorage.delete(user1));
        System.out.println(userStorage.delete(user2));
    }
}
