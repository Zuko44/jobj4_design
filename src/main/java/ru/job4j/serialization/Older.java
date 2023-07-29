package ru.job4j.serialization;

import org.json.JSONPropertyIgnore;

public class Older {
    private Younger b;

    @JSONPropertyIgnore
    public Younger getB() {
        return b;
    }

    public void setB(Younger b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}
