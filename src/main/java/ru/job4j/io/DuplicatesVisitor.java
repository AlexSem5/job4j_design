package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public Map<FileProperty, List<Path>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file.toAbsolutePath());
        } else {
            List<Path> list = new ArrayList<>();
            map.put(fileProperty, list);
            list.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}



