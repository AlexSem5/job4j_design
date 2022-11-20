package ru.job4j.regex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс описывает программу, которая осуществляет обход дерева директорий,
 * включая вложенные директории, и ищет файлы по определённому предикату.
 * Имя файла может задаваться, целиком, по маске, по регулярному выражению.
 * Программа должна запускаться с параметрами, например:  -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * Ключи:
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 */
public class RegExSearch {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Four arguments are required");
        }
        NewArgsName regEx = NewArgsName.of(args);
        validate(regEx);
        String name = regEx.get("n");
        Predicate<Path> condition = null;
        if ("name".equals(regEx.get("t"))) {
            condition = p -> p.toFile().getName().equals(name);
        }
        if ("regex".equals(regEx.get("t"))) {
            condition = p -> p.toFile().getName().matches(name);
        }
        if ("mask".equals(regEx.get("t"))) {
            String convert = maskToRegex(name);
            condition = p -> p.toFile().getName().matches(convert);
        }
        List<Path> paths = search(Paths.get(regEx.get("d")), condition);
        save(paths, regEx.get("o"));
    }
    
    /**
     * Для обхода дерева директорий используется класс VisitFiles.
     *
     * @param root      начальная директория
     * @param condition условие поиска файла
     * @return коллекция, хранящая найденные файлы
     */
    public static List<Path> search(Path root, Predicate<Path> condition) {
        VisitFiles searcher = new VisitFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }
    
    /**
     * Метод записывает результат поиска в файл
     * @param paths коллекция, в которой хранятся найденные файлы.
     * @param target  имя файла
     */
    private static void save(List<Path> paths, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(target)))) {
            paths.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Метод реализует преобразование макси в регулярное
     * выражение
     *
     * @param mask маска
     * @return регулярное выражение
     */
    public static String maskToRegex(String mask) {
        return mask.replace(".", "[.]")
                   .replace("*", ".*")
                   .replace("?", ".");
    }
    
    /**
     * Метод реализует валидацию входных параметров
     *
     * @param argsName объект, предоставляющий доступ ко входным параметрам
     *                 типа ключ-значение
     */
    
    public static boolean validate(NewArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Does not exist %s", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not a directory %s", path.toAbsolutePath()));
        }
        if (!"mask".equals(argsName.get("t")) && !"name".equals(argsName.get("t")) && !"regex".equals(argsName.get("t"))) {
            throw new IllegalArgumentException("Search type should be either \"mask\" or \"name\" or \"regex\"");
        }
        if (!argsName.get("n").matches("^[\\w*?]+[.][*?0-9a-z_]+$")) {
            throw new IllegalArgumentException("Incorrect file name");
        }
        String target = argsName.get("o");
        if (!target.matches("^[\\w\\\\:]+[.][0-9a-z_]+$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect target name: %s ", target));
        }
        return true;
    }
}
