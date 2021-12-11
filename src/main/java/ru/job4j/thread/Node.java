package ru.job4j.thread;


/**
 * Immutable объекты.
 * Задание:
 * 1. Ниже приведен код не потокобезопасного класса,
 *    описывающего узел односвязного списка.
 * 2. Сделайте этот класс Immutable.
 *
 * Правила создания Immutable объекта.
 * 1. Все поля отмечены final.
 * 2. Immutable Класс не меняет состояние объекта после создания объекта.
 */
public final class Node<T> {
    private final Node<T> next;
    private final T value;

    /*
    Генерируем конструктор.
    оставляем get,
    убираем set
     */
    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    /*
    public void setNext(Node<T> next) {
        this.next = next;
    }
     */

    public T getValue() {
        return value;
    }

    /*
    public void setValue(T value) {
        this.value = value;
    }
     */
}
