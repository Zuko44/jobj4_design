package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        System.out.println(log);
    }

    public List<String> filter(String file) {
        List<String> log = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            /**String substring = " 404 ";
             for (String line = in.readLine(); line != null; line = in.readLine()) {
             if (line.contains(substring)) {
             log.add(line);
             log.add(System.lineSeparator());
             }
             }*/
            in.lines().filter(line -> line.contains(" 404 "))
                    .map(line -> line + System.lineSeparator()).forEach(log::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }
}
