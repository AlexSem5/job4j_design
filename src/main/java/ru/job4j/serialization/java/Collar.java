package ru.job4j.serialization.java;

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
}
