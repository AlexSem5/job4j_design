package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    /**
     * Класс описывает ратоту программы 'Консольный чат':
     * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
     * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * - при вводе слова «закончить» программа прекращает работу.
     * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
     */
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    /**
     * имя файла, в который будет записан весь диалог между ботом и пользователем
     */
    private final String path;

    /**
     * имя файла в котором находятся строки с ответами, которые будет использовать бот
     */
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата
     */
    public void run() {
        boolean run = true;
        List<String> list = new ArrayList<>();
        while (run) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            list.add(input);
            if (input.equals(STOP)) {
                String input1 = scanner.nextLine();
                while (!input1.equals(CONTINUE)) {
                    list.add(input1);
                }
                list.add(input1);
            }
            if (input.equals(OUT)) {
                saveLog(list);
                run = false;
                continue;
            }
            int index = (int) (Math.random() * readPhrases().size());
            list.add(readPhrases().get(index));
            System.out.println(readPhrases().get(index));
        }
    }

    /**
     * Метод читает фразы из файла с ответами бота botAnswers
     *
     * @return список фраз
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            read.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод сохраняет лог чата в файл
     *
     * @param log диалог между ботом и пользователем
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(path)))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\data\\path.txt", ".\\data\\botAnswers.txt");
        cc.run();
    }
}
