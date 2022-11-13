package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Класс описывает пример работы с JAXB (Java Architecture for XML Binding).
 * Сохраняем Java объект в XML - строку и восстанавливаем объект из XML -строки.
 */
public class JaxbDog {
    public static void main(String[] args) {
        Dog dog = new Dog(true, 5, new Collar(7), new String[] {"Ivan", "Vasiliy"});
        String xml = convertToXml(dog);
        convertToObject(xml);
    }
    
    /**
     * Метод реализует сохранение объекта типа Dog в XML-строку
     *
     * @param dog
     */
    private static String convertToXml(Dog dog) {
        String xml = "";
        try {
            /* Cоздаём объект JAXBContext - точку входа (контекст) для доступа к JAXB*/
            JAXBContext context = JAXBContext.newInstance(Dog.class);
            /* Создаем сериализатор */
            Marshaller marshaller = context.createMarshaller();
            /* Указываем, что нам нужно форматирование
             * (устанавливаем флаг для читабельного вывода XML в JAXB) */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            /* Сериализация(маршаллинг, сохранение) объекта в строку*/
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(dog, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
    
    /**
     * Метод реализует восстановление объекта из XML-строки
     *
     * @param xml
     * @return
     */
    private static Dog convertToObject(String xml) {
        Dog result = null;
        try {
            /* Cоздаём объект JAXBContext - точку входа (контекст) для доступа к JAXB*/
            JAXBContext context = JAXBContext.newInstance(Dog.class);
            /* Создаем десериализатор */
            Unmarshaller unmarshaller = context.createUnmarshaller();
            /* Восстанавливаем объект из XML-строки */
            StringReader reader = new StringReader(xml);
            result = (Dog) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}

