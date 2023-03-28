package ru.job4j.collection;

import java.util.*;

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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 12);
        /**
         User user1 = new User("Ken", 11, new GregorianCalendar(2022, 1, 24));
         User user2 = new User("Ken", 11, new GregorianCalendar(2022, 1, 24));
         * hashOne - 1389133897 ,hash1 - 1389154949 ,bucket1 - 5
         * hashTwo - 1534030866 ,hash2 - 1534013309 ,bucket2 - 13
         */
        User user1 = new User("Ken", 11, calendar);
        User user2 = new User("Ken", 11, calendar);
        /**
         * hash переопределён
         * hashOne - -889848241 ,hash1 - 889861306 ,bucket1 - 10
         * hashTwo - -889848241 ,hash2 - 889861306 ,bucket2 - 10
         */
        int hashOne = user1.hashCode();
        int hash1 = hashOne ^ (hashOne >> 16);
        int bucket1 = hash1 & 15;
        int hashTwo = user2.hashCode();
        int hash2 = hashTwo ^ (hashTwo >> 16);
        int bucket2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("hashOne - " + hashOne + " ,hash1 - " + hash1 + " ,bucket1 - " + bucket1);
        System.out.print("hashTwo - " + hashTwo + " ,hash2 - " + hash2 + " ,bucket2 - " + bucket2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(children, user.children)
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }
    /**
     * equals переопределён
     * hashOne - 1389133897 ,hash1 - 1389154949 ,bucket1 - 5
     * hashTwo - 1534030866 ,hash2 - 1534013309 ,bucket2 - 13
     */
}
