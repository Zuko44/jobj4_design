package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> logs = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logs.txt", "./data/answers.txt");
        cc.run();
    }

    public void run() {
        List<String> words = readPhrases();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фразу/слово: ");
        String request = scanner.nextLine();
        logs.add(request);
        boolean flag = false;
        while (!OUT.equals(request)) {
            if (STOP.equals(request)) {
                flag = true;
            } else if (CONTINUE.equals(request)) {
                flag = false;
            }
            if (!flag) {
                String answer = words.get(new Random().nextInt(words.size()));
                System.out.println(answer);
                logs.add(answer);
            }
            request = scanner.nextLine();
            logs.add(request);
        }
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            rsl = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> logs) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (String log : logs) {
                writer.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
