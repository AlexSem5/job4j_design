package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс описывает программу, которая осуществляет обход дерева директорий,
 * включая вложенные директории, и ищет файлы по определённому предикату.
 *
 */
public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Для обхода дерева директорий используется класс SearchFiles.
     * @param root начальная директория
     * @param condition условие поиска файла
     * @return коллекция, хранящая найденные файлы
     * @throws IOException
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Two arguments are required");
        }
        File file = Paths.get(args[0]).toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].matches("^[.][0-9a-z_]+$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect file extension: %s ", args[1]));
        }
    }
}
