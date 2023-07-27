package ru.job4j.serialization;

import java.util.Arrays;

public class Slave {
    private boolean sex;
    private int age;
    String sobriquet;
    private Contact contact;
    private String[] owners;

    public Slave(boolean sex, int age, String sobriquet, Contact contact, String[] owners) {
        this.sex = sex;
        this.age = age;
        this.sobriquet = sobriquet;
        this.contact = contact;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Slave{"
                + "sex=" + sex
                + ", age=" + age
                + ", sobriquet='" + sobriquet + '\''
                + ", contact=" + contact
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}
