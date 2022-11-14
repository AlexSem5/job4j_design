package ru.job4j.io.serialization.json;

public class Collar {
    private final int size;
    
    public Collar(int size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        return "Collar{"
               + "size=" + size + '}';
    }
    
    public int getSize() {
        return size;
    }
}
