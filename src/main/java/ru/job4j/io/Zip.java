package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
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
