package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/**
 * Класс описывает объект, подлежащий сохранению в xml-формат
 * и обратному преобразованию.
 */

@XmlRootElement(name = "dog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dog {
    @XmlAttribute
    private boolean isPet;
    @XmlAttribute
    private int weight;
    private Collar collar;
    
    @XmlElementWrapper(name = "dogFriends")
    @XmlElement(name = "dogFriend")
    private String[] dogFriends;
    
    public Dog(boolean isPet, int weight, Collar collar, String[] dogFriends) {
        this.isPet = isPet;
        this.weight = weight;
        this.collar = collar;
        this.dogFriends = dogFriends;
    }
    
    public Dog() {
    }
    
    @Override
    public String toString() {
        return "Dog{" + "isPet=" + isPet + ", weight=" + weight
               + ", collar=" + collar
               + " , dogFriends=" + Arrays.toString(dogFriends)
               + '}';
    }
}
