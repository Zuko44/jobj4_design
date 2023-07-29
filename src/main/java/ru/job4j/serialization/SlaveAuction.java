package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SlaveAuction {
    public static void main(String[] args) {

        /** JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /** JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /** JSONObject напрямую методом put */
        final Slave person = new Slave(false, 30, "Empty", new Contact(11111, "11-111"),
                new String[]{"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("age", person.getSobriquet());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        jsonObject.put("owners", person.getOwners());

        /** Выведем результат в консоль */
        System.out.println(jsonObject);

        /** Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}
