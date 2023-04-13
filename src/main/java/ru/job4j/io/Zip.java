package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        Zip zip2 = new Zip();
        ArgsName arguments = ArgsName.of(args);
        String directory = arguments.get("d");
        String exclude = arguments.get("e");
        File output = new File(arguments.get("o"));
        validate(args.length, directory);
        List<File> files = Search.search(
                        Paths.get(directory),
                        t -> !t.toFile().getName().endsWith(exclude)
                ).stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        zip2.packFiles(files, output);
    }

    private static void validate(int size, String directory) {
        if (size != 3) {
            throw new IllegalArgumentException("arguments must be 3");
        }
        File file = new File(directory);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("invalid path");
        }
    }

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
