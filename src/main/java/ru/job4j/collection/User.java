package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Ken", 11, new GregorianCalendar(2022, 1, 24));
        User user2 = new User("Ken", 11, new GregorianCalendar(2022, 1, 24));
        int hashOne = user1.hashCode();
        int hash1 = hashOne ^ (hashOne >> 16);
        int bucket1 = hash1 & 15;
        /**
         * hashOne - 1389133897 ,hash1 - 1389154949 ,bucket1 - 5
         */
        int hashTwo = user2.hashCode();
        int hash2 = hashTwo ^ (hashTwo >> 16);
        int bucket2 = hash2 & 15;
        /**
         * hashTwo - 1534030866 ,hash2 - 1534013309 ,bucket2 - 13
         */
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("hashOne - " + hashOne + " ,hash1 - " + hash1 + " ,bucket1 - " + bucket1);
        System.out.print("hashTwo - " + hashTwo + " ,hash2 - " + hash2 + " ,bucket2 - " + bucket2);
    }
}
