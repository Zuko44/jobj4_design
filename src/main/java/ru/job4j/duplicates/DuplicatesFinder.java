package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicate = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicate);
        duplicate.getResult();
    }
}
