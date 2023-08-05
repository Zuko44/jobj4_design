package ru.job4j.ioexam;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("arguments must be 4");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        save(argsName);
    }

    /**
     * Сохранение пути до найденных файлов в документ
     */
    public static void save(ArgsName argsName) {
        Path path = Path.of(argsName.get("d"));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))
                ))) {
            if ("name".equals(argsName.get("t"))) {
                search(path, p -> p.toFile().getName().equals(argsName.get("n"))).forEach(out::println);
            } else if ("mask".equals(argsName.get("t"))) {
                FileFilter fileFilter = new FileFilter(preparePattern(argsName.get("n")));
                search(path, p -> fileFilter.accept(path.toFile(), p.toFile().getName())).forEach(out::println);
            } else {
                Pattern pattern = Pattern.compile(argsName.get("n"));
                search(path, p -> pattern.matcher(p.toFile().getName()).matches()).forEach(out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Поиск файлов и возврат List-а с файлами
     */
    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searcher.getPaths();
    }

    /**
     * Валидация параметров командной строки
     */
    private static void validate(ArgsName args) {
        Path path = Path.of(args.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("directory does not exist");
        }
        if (args.get("n").length() < 1) {
            throw new IllegalArgumentException("please, set the correct name or mask");
        }
        if (!("mask".equals(args.get("t")) || "name".equals(args.get("t")))) {
            throw new IllegalArgumentException("the type of search mast be mask or name");
        }
        if (args.get("o").length() < 1) {
            throw new IllegalArgumentException("wrong path");
        }
    }

    /**
     * Здесь, если передаётся маска, производится экранирование символов
     */
    private static String preparePattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c == '*') {
                sb.append(".*");
            } else if (c == '.') {
                sb.append("\\.");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
