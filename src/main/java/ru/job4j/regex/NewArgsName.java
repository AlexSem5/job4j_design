package ru.job4j.regex;

import java.util.HashMap;
import java.util.Map;

public class NewArgsName {
    /**
     * Хранение пар ключ-значение осуществляется в коллекции типа HashMap
     */
    private final Map<String, String> values = new HashMap<>();
    
    /**
     * Метод осуществляет получение значения по ключу
     *
     * @param key ключ
     * @return значение
     */
    
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }
    
    /**
     * Метод осуществляет разбиение строк на пары ключ-значение
     *
     * @param args массив строк, описывающий параметры для работы с виртуальной машиной Java
     */
    private void parse(String[] args) {
        validate(args);
        for (String arg : args) {
            String[] lines = arg.substring(1).split("=", 2);
            values.put(lines[0], lines[1]);
        }
    }
    
    /**
     * Вспомогательный метод. Создаёт экземпляр класса
     * и вызывает на нём метод
     *
     * @param args массив строк, описывающий параметры для работы с виртуальной машиной Java
     * @return возвращает экземпляр класса
     */
    public static NewArgsName of(String[] args) {
        NewArgsName names = new NewArgsName();
        names.parse(args);
        return names;
    }
    
    /**
     * Метод осуществляет проверку массива строк на соответствие шаблону
     *
     * @param args массив строк, описывающий параметры для работы с виртуальной машиной Java
     */
    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String arg : args) {
            if (arg.isEmpty() || !arg.contains("=") || !arg.startsWith("-") || arg.charAt(1) == '='
                || (arg.endsWith("=") && arg.indexOf("=") == arg.length() - 1)) {
                throw new IllegalArgumentException();
            }
        }
    }
}

