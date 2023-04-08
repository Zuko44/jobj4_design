package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    /**
     * @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
     * System.out.println(file.toAbsolutePath());
     * return super.visitFile(file, attrs);
     * }
     */
    /**
     * private Map<Path, FileProperty> duplicate = new HashMap<>();
     * private Map<FileProperty, Path> straight = new HashMap<>();
     */
    private Map<FileProperty, List<Path>> duplicate = new HashMap<>();

    public void getResult() {
        for (Map.Entry<FileProperty, List<Path>> entry : duplicate.entrySet()) {
            FileProperty key = entry.getKey();
            List<Path> value = entry.getValue();
            if (value.size() > 1) {
                System.out.println(value.get(0) + ", name - " + key.getName() + ", size - " + key.getSize() + "kb");
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            FileProperty fileProp = new FileProperty(attrs.size(), file.getFileName().toString());
            /**if (straight.containsKey(fileProp)) {
             duplicate.put(straight.get(fileProp), fileProp);
             duplicate.put(file.normalize(), fileProp);
             } else {
             straight.put(fileProp, file.normalize());
             }*/
            /**if (duplicate.containsKey(fileProp)) {
             duplicate.get(fileProp).add(file.normalize());
             } else {
             List<Path> list = new ArrayList<>();
             list.add(file.normalize());
             duplicate.put(fileProp, list);
             }
             */
            List<Path> list = new ArrayList<>();
            list.add(file.normalize());
            if (duplicate.putIfAbsent(fileProp, list) != null) {
                duplicate.get(fileProp).add(file.normalize());
            }
        }
        return super.visitFile(file, attrs);
    }
}
