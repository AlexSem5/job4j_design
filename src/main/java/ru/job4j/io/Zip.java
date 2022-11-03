package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *Класс описывает утилиту для архивации директории.
 * ТЗ:
 * 1. При запуске указывается папка, которую мы хотим архивировать.
 * 2. В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта, то есть включать вложенные директории.
 * 4. Для работы со входными аргументами используется класс ArgsName.
 * 5. Входные параметры:
 * -d - directory - которую мы хотим архивировать.
 * -e - exclude - исключить файлы с расширением class.
 * -o - output - во что мы архивируем.
 * 6. Для поиска и фильтрации файлов используется класс Search.
 *
 *  @version 1.0
 *  @author  ALEKSANDR S
 */
public class Zip {
    /**
     * Метод, реализующий архивацию директории.
     * @param sources коллекция типа List, осуществляющая хранение объектов,
     *               представляющих имя и путь к файлу или директории
     * @param target объект, представляющий имя и путь к директории архива
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализует валидацию входных параметров
     * @param argsName объект, предоставляющий доступ ко входным параметрам
     *                 типа ключ-значение
     */
    private static void validate(ArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Does not exist %s", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not a directory %s", path.toAbsolutePath()));
        }
        String extension = argsName.get("e");
        if (!extension.matches("^[.][0-9a-z_]+$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect file extension: %s ", extension));
        }
        String target = argsName.get("o");
        if (!target.matches("^\\w+[.]zip$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect target name: %s ", target));
        }
    }

    /**
     * Метод осуществляет запуск утилиты.
     * Для обхода дерева директорий используется класс Search.
     * Метод Files.walkFileTree(Path start, FileVisitor<? super Path> visitor).
     * Path start - начальная директория
     * Интерфейс FileVisitor<T> содержит метод visitFile, который вызывается
     * для каждого файла в директории, включая вложенные директории.
     *
     * P.S. В классе DuplicatesVisitor представлен альтернативный подход
     * с использованием класса SimpleFileVisitor<Path>
     *
     * @param args  Входной параметр вида -d=c:\projects\job4j_design -e=.java -o=project.zip
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Three arguments are required");
        }
        ArgsName zip = ArgsName.of(args);
        validate(zip);
        Path start = Paths.get(zip.get("d"));
        List<Path> list = Search.search(start, p -> !p.toFile().getName().endsWith(zip.get("e")));
        Zip zip1 = new Zip();
        zip1.packFiles(list, new File(zip.get("o")));
    }
}
