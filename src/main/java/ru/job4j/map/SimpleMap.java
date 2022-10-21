package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            if (entry != null) {
                tableNew[indexFor(hash(Objects.hashCode(entry.key)))] = entry;
            }
        }
        table = tableNew;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            return null;
        }
        if (hash(Objects.hashCode(key)) == hash(Objects.hashCode(table[index].key))
            && Objects.equals(key, table[index].key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            return false;
        }
        if (hash(Objects.hashCode(key)) == hash(Objects.hashCode(table[index].key))
            && Objects.equals(key, table[index].key)) {
            table[index] = null;
            modCount++;
            count--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int i = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (i < capacity && table[i] == null) {
                    i++;
                }
                return i < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
