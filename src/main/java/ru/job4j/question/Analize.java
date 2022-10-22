package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        int changed = 0;
        int deleted = 0;
        for (User user : current) {
            map.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (map.get(user.getId()) != null && !map.get(user.getId()).equals(user.getName())) {
                changed++;
            }
            if (map.get(user.getId()) == null) {
                deleted++;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}

