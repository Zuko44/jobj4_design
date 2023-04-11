package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    public String get(String key) {
        String str = values.get(key);
        if (str == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String elem : args) {
            validate(elem);
            String[] str = elem.split("=", 2);
            values.put(str[0].replace("-", ""), str[1]);
        }
    }

    private void validate(String str) {
        String[] str2 = str.split("=", 2);
        if (!str.contains("=")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", str));
        }
        if (str.charAt(0) != '-') {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", str));
        }
        if (str2[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", str));
        }
        if (Objects.equals(str2[0], "-")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", str));
        }
    }
}
