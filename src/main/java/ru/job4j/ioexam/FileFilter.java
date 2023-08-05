package ru.job4j.ioexam;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {

    private String fileMask;

    public FileFilter(String fileMask) {
        this.fileMask = fileMask;
    }

    @Override
    public boolean accept(File directory, String fileName) {
        return (fileName.matches(this.fileMask));
    }
}
