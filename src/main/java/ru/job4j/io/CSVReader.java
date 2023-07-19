package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] filters = argsName.get("filter").split(",");
        int[] index = new int[filters.length];
        List<String> result = new ArrayList<>();
        try (BufferedReader inputFile = new BufferedReader(new FileReader(argsName.get("path")));
             PrintWriter fileOut = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream(argsName.get("out"))))) {
            var scanner = new Scanner(inputFile)
                    .useDelimiter(",");
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < line.length; i++) {
                    for (int j = 0; j < filters.length; j++) {
                        if (line[i].equals(filters[j])) {
                            index[j] = i;
                        }
                    }
                }
                StringJoiner stringJoiner = new StringJoiner(argsName.get("delimiter"));
                for (int column : index) {
                    stringJoiner.add(line[column]);
                }
                result.add(stringJoiner.toString());
                result.add(System.lineSeparator());
            }

            if (argsName.get("out").equals("stdout")) {
                for (String str : result) {
                    System.out.println(str);
                }
            } else {
                for (String str : result) {
                    fileOut.print(str);
                }
                System.out.println("recording completed successfully");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    public static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("arguments must be 4");
        }
        String[] str = args[0].split("=", 2);
        if (!str[1].contains(".csv")) {
            throw new IllegalArgumentException("invalid path");
        }
        String[] str2 = args[1].split("=", 2);
        if (!str2[1].contains(";") || str2[1].length() > 1) {
            throw new IllegalArgumentException("wrong delimiter");
        }
        String[] str3 = args[2].split("=", 2);
        if (!(str3[1].contains("stdout") || str3[1].contains(".csv"))) {
            throw new IllegalArgumentException("wrong path");
        }
        String[] str4 = args[3].split("=", 2);
        String[] filters = str4[1].split(",");
        if (filters.length < 1) {
            throw new IllegalArgumentException("there must be at least one filter");
        }
    }
}
