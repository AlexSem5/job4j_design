package ru.job4j.io.serialization.json;

import org.json.JSONObject;

/**
 * Класс описывает преобразование (сериализация, сохранение) объекта в json-строку.
 * Note: Although we have a way to serialize a Java object to JSON string,
 * there is no way to convert it back using this library.
 * If we want that kind of flexibility, we can switch to other libraries such as Jackson(
 * не всегда нужно преобразование в Java-объект, json-строка используется как входящая
 * или исходящая информация в структурированном виде - набор пар "ключ":"значение".
 * По ключу извлекаете значение и используете его).
 */
public class JsonDog {
    public static void main(String[] args) {
        final Dog dog = new Dog(true, 5, new Collar(7), new String[] {"Ivan", "Vasiliy"});
        /* Преобразование объекта dog в json-строку */
        String jsonObject = new JSONObject(dog).toString();
        System.out.println(jsonObject);
        /* Преобразование объекта dog в JSONObject с помощью метода put */
        JSONObject jsonObjectNew = new JSONObject();
        jsonObjectNew.put("pet", dog.isPet());
        jsonObjectNew.put("weight", dog.getWeight());
        jsonObjectNew.put("collar", dog.getCollar());
        jsonObjectNew.put("friends", dog.getDogFriends());
        System.out.println(jsonObjectNew);
    }
}
