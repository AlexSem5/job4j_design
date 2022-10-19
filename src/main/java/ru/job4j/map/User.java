package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Bob", 3, birthday);
        User user2 = new User("Bob", 3, birthday);
        int n = 16;
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & (n - 1);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & (n - 1);
        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("User 1 - hashcode: %s, hash: %s, bucket: %s", hashCode1, hash1, bucket1);
        System.out.println();
        System.out.printf("User 2 - hashcode: %s, hash: %s, bucket: %s", hashCode2, hash2, bucket2);
    }
}
