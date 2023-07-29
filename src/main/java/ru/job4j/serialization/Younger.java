package ru.job4j.serialization;

import org.json.JSONObject;

public class Younger {
    private Older a;

    public Older getA() {
        return a;
    }

    public void setA(Older a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Older a = new Older();
        Younger b = new Younger();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));
    }
}
