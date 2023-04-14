package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName arguments = ArgsName.of(args);
        Path directory = Path.of(arguments.get("d"));
        String exclude = arguments.get("e");
        File output = new File(arguments.get("o"));
        validate(args.length, directory, exclude, output);
        List<Path> files = Search.search(
                directory,
                t -> !t.toFile().getName().endsWith(exclude)
        );
        zip.packFiles(files, output);
    }

    private static void validate(int size, Path directory, String exclude, File output) {
        if (size != 3) {
            throw new IllegalArgumentException("arguments must be 3");
        }
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("invalid path");
        }
        if (exclude.charAt(0) != '.') {
            throw new IllegalArgumentException("wrong format");
        }
        if (!output.getName().endsWith(".zip")) {
            throw new IllegalArgumentException("format must be zip");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
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
