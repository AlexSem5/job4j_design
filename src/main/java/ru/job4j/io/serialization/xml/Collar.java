package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "collar")
public class Collar {
    @XmlAttribute
    private int size;
    
    public Collar(int size) {
        this.size = size;
    }
    
    public Collar() {
    }
    
    @Override
    public String toString() {
        return "Collar{"
               + "size=" + size + '}';
    }
}
