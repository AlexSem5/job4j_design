package ru.job4j.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс описывает работу программы, которая читает данные из CSV файла и выводит их.
 * В качестве входных данных задается путь к файлу path, разделитель delimiter,
 * приемник данных out и фильтр по столбцам filter.
 * Ключ -out имеет только два допустимых значения: stdout или путь к файлу, куда нужно вывести.
 * <p>
 * Например, если есть файл CSV со столбцами name, age, birthDate, education, children и
 * программа запускается таким образом:
 * -path=file.csv -delimiter=;  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.csv и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 */
public class CSVReader {
    /**
     * Метод сопоставляет массив требуемых имен столбцов и первую строку источника данных -
     * в ней передаются имена столбцов. Затем осуществляется чтение последующих строк,
     * преобразовывание каждой в массив слов и фильтрация по столбцам.
     *
     * @param argsName объект, предоставляющий доступ ко входным параметрам
     *                 типа ключ-значение
     */
    public static void handle(ArgsName argsName) {
        List<String> filter = Arrays.asList(argsName.get("filter").split(","));
        List<Integer> indices = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")));
             PrintWriter writer = new PrintWriter(new FileWriter(argsName.get("out")))) {
            String delimiter = argsName.get("delimiter");
            int index = 0;
            while (scanner.hasNext()) {
                String[] columns = scanner.nextLine().split(delimiter);
                StringJoiner joiner = new StringJoiner(delimiter);
                for (int i = 0; i < columns.length; i++) {
                    if (indices.contains(i)) {
                        joiner.add(columns[i]);
                    }
                    if (filter.contains(columns[i])) {
                        indices.add(index);
                        joiner.add(columns[i]);
                    }
                    index++;
                }
                if ("stdout".equals(argsName.get("out"))) {
                    System.out.println(joiner);
                }
                if (!"stdout".equals(argsName.get("out"))) {
                    writer.println(joiner);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод реализует валидацию входных параметров
     *
     * @param argsName объект, предоставляющий доступ ко входным параметрам
     *                 типа ключ-значение
     */
    private static void validate(ArgsName argsName) {
        Path source = Paths.get(argsName.get("path"));
        if (!Files.exists(source)) {
            throw new IllegalArgumentException(String.format("Does not exist %s", source.toAbsolutePath()));
        }
        String delimiter = argsName.get("delimiter");
        if (!delimiter.matches("^;$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect delimiter: %s ", delimiter));
        }
        Path target = Paths.get(argsName.get("out"));
        if (!"stdout".equals(target.toString()) && !Files.exists(target)) {
            throw new IllegalArgumentException(String.format("Does not exist %s", target.toAbsolutePath()));
        }
        if (!target.toString().matches("^\\w+[.]csv$") || !source.toString().matches("^\\w+[.]csv$")) {
            throw new IllegalArgumentException(
                    String.format("incorrect file extension: %s or %s ", target, source));
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("filter is empty");
        }
    }

    /**
     * Метод осуществляет запуск программы.
     *
     * @param args входной параметр вида -path=file.csv -delimiter=;  -out=stdout(путь target) -filter=name,age(или другие)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Four arguments are required");
        }
        ArgsName scv = ArgsName.of(args);
        validate(scv);
        handle(scv);
    }
}

