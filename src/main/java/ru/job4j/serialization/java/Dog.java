package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * JSON (JavaScript Object Notation) – текстовый формат обмена данными,
 * основан на синтаксисе JavaScript.
 * <p>
 * Применение:
 * наиболее часто применяется для пересылки информации между браузером
 * и сервером (загрузка контента по технологии Ajax)
 * или между веб-сайтами (различные HTTP-соединения).
 * можно использовать везде, где требуется обмен данных между программами;
 * хранение локальной информации (например, настроек);
 * за счёт лаконичности может быть выбран для сериализации сложных структур вместо XML.
 */

public class Dog {
    private final boolean isPet;
    private final int weight;
    private final Collar collar;
    private final String[] dogFriends;
    
    public Dog(boolean isPet, int weight, Collar collar, String[] dogFriends) {
        this.isPet = isPet;
        this.weight = weight;
        this.collar = collar;
        this.dogFriends = dogFriends;
    }
    
    @Override
    public String toString() {
        return "Dog{" + "isPet=" + isPet + ", weight=" + weight
               + ", collar=" + collar
               + " , dogFriends=" + Arrays.toString(dogFriends)
               + '}';
    }
    
    public static void main(String[] args) {
        final Dog dog = new Dog(true, 5, new Collar(7), new String[] {"Ivan", "Vasiliy"});
        
        /* Преобразуем объект dog в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(dog));
        
        /* Модифицируем json-строку */
        final String dogJson =
                "{"
                + "\"isPet\":true,"
                + "\"weight\":5,"
                + "\"collar\":"
                + "{"
                + "\"size\":\"7\""
                + "},"
                + "\"dogFriends\":"
                + "[\"Ivan\",\"Vasiliy\"]"
                + "}";
        final Dog dogMod = gson.fromJson(dogJson, Dog.class);
        System.out.println(dogMod);
    }
}
