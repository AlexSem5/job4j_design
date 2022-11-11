package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

/**
 * Класс описывает сериализацию - процесс преобразования объектов
 * в бинарный (т.е. последовательность битов) или текстовый формат.
 *
 * Механизм сериализации/десериализации используется для сохранения
 * состояния программы между запусками, хранения настроек, передачи
 * данных между программами локально или по сети.
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;
    
    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }
    
    public int getZipCode() {
        return zipCode;
    }
    
    public String getPhone() {
        return phone;
    }
    
    @Override
    public String toString() {
        return "Contact{"
               + "zipCode=" + zipCode
               + ", phone='" + phone + '\''
               + '}';
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        
        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
