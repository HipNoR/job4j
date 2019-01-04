package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Class contains chat logic.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.01.2019
 */
public class ChatLogic {

    /**
     * The method starts the chat.
     * @param inputFile source of phrases.
     * @throws IOException if exception.
     */
    public void startChat(String inputFile) throws IOException {
        Scanner in = new Scanner(System.in);
        boolean work = true;
        boolean pause = false;
        File input = new File(ChatLogic.class.getClassLoader().getResource(inputFile).getPath());
        File log = new File("ChatLog.txt");
        try (var raf = new RandomAccessFile(input, "r");
             var fw = new FileWriter(log, true)
        ) {
            StringBuilder sb = new StringBuilder();
            sb.append("Добро пожаловать в чат!\n");
            sb.append("Введите любую фразу и вы получите случайный ответ.\n");
            sb.append("Для приостановки введите \"стоп\", для продолжения введите \"продолжить\".\n");
            sb.append("Для выхода из программы введите \"закончить\".\n");
            System.out.println(sb);
            fw.write(String.format("[%s] Чат начал работу \n", new Date()));
            String query;
            String answer;
            while (work) {
                System.out.print("Ваша фраза: ");
                query = in.nextLine().toLowerCase();
                fw.write(String.format("[%s] - {query} - %s \n", new Date(), query));
                if (query.equals("закончить")) {
                    work = false;
                    pause = true;
                    System.out.println("Работа приложения закончена! Всего доброго!");
                    fw.write(String.format("[%s] Работа приложения закончена! \n-------\n", new Date()));
                } else if (query.equals("стоп")) {
                    pause = true;
                    fw.write(String.format("[%s] Чат приостановлен \n", new Date()));
                } else if (query.equals("продолжить")) {
                    pause = false;
                    fw.write(String.format("[%s] Чат возабновил работу \n", new Date()));
                }
                if (!pause) {
                    answer = getPhrase(raf);
                    System.out.println("Ответ:" + answer);
                    fw.write(String.format("[%s] - {answer} - %s \n", new Date(), answer));
                } else if (work) {
                    System.out.println("Работа робота приостановлена, для продолжения введите \"продолжить\"\n"
                            + "или введите \"закончить\" для выхода из программы.");
                }
            }
        }
    }


    /**
     * The method returns random line from txt file.
     * If pointer reaches end of the file than returns first line,
     * regardless of whether the line-breaker is at the end of the file.
     *
     * The pointer moves to a random position and moves to a newline character.
     * Then returns the next line.
     *
     * @param ra RandomAccessFile object.
     * @return line from file.
     * @throws IOException if exception.
     */
    public String getPhrase(RandomAccessFile ra) throws IOException {
        long size = ra.length();
        ra.seek(Math.round(Math.random() * size));
        int temp;
        do {
            temp = ra.read();
            if (temp == -1 || ra.getFilePointer() == size) {
                ra.seek(0);
                break;
            }
        } while (temp != 10);
        return new String(ra.readLine().getBytes(StandardCharsets.ISO_8859_1), UTF_8);
    }
}
