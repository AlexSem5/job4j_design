package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:\\projects\\job4j_design");
        findDuplicate(start);
    }

    public static void findDuplicate(Path root) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(root, duplicatesVisitor);
        duplicatesVisitor.getMap().entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                    System.out.printf("%s - %dB%n", entry.getKey().getName(),
                            entry.getKey().getSize());
                    entry.getValue().forEach(System.out::println);
                });
    }
}
