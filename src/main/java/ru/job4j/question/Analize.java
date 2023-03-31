package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info diff = new Info(0, 0, 0);
        Map<Integer, User> map = new HashMap<>(current.size());
        for (User user : previous) {
            map.put(user.getId(), user);
        }
        for (User user : current) {
            if (!previous.contains(user)) {
                if (map.get(user.getId()) == null) {
                    diff.setAdded(1);
                } else {
                    diff.setChanged(1);
                }
            }
        }
        diff.setDeleted(previous.size() - current.size() + diff.getAdded());
        return diff;
    }
}
