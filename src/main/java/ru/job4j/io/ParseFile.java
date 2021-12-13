package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

/**
 * Visibility. Общий ресурс вне критической секции.
 * Задание:
 * 1. Ниже приведен код, класс парсера файла.
 * 2. Поправьте код с ошибками в коде:
 * - Избавиться от get set за счет передачи File в конструктор.
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста.
 *   Нужно применить шаблон стратегия: content(Predicate^Character> filter).
 *
 *   Для решения, дополнительно создадим класс OutPutFile и интерфес Connector.
 */
public final class ParseFile implements Connector {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content((i) -> true);
    }

    public String getContentWithoutUnicode() {
        return content((i) -> i < 0x80);
    }

    @Override
    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > -1) {
                output.append((char) data);
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
