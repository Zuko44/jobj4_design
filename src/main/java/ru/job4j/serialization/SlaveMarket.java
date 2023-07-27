package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SlaveMarket {
    public static void main(String[] args) {
        Slave slave = new Slave(true, 32, "blackfoot", new Contact(11111, "11-111"),
                new String[]{"Mr Bennett Daddy", "Dicky Speck"});

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(slave));

        String personSlave =
                "{"
                        + "\"sex\":true,"
                        + "\"age\":32,"
                        + "\"sobriquet\":nigger,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":\"11111\","
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"owners\":"
                        + "[\"Ace Speck\",\"Calvin J. Candie\"]"
                        + "}";
        Slave end = gson.fromJson(personSlave, Slave.class);
        System.out.println(end);
    }
}
