package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
     * System.out.println(file.toAbsolutePath());
     * return super.visitFile(file, attrs);
     * }
     */
    private Map<Path, FileProperty> duplicate = new HashMap<>();
    private Map<FileProperty, Path> straight = new HashMap<>();

    public Map<Path, FileProperty> getDuplicate() {
        return duplicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
            if (straight.containsKey(fileProp)) {
                duplicate.put(straight.get(fileProp), fileProp);
                duplicate.put(file.normalize(), fileProp);
            } else {
                straight.put(fileProp, file.normalize());
            }
        }
        return super.visitFile(file, attrs);
    }
}
