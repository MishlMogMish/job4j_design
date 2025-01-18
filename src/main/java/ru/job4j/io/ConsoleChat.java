package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isBotPaused = false;
        List<String> botResponses = readPhrases();
        if (botResponses.isEmpty()) {
            throw new IllegalStateException("BotResponses is empty, can not start the bot");
        }

        Iterator<String> responseIterator = botResponses.iterator();
        List<String> chatLog = new ArrayList<>();
        boolean isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                String consoleInput = scanner.nextLine()
                        .trim();
                chatLog.add(consoleInput);

                if (OUT.equals(consoleInput)) {
                    isRunning = false;
                }
                if (STOP.equals(consoleInput)) {
                    isBotPaused = true;
                }
                if (CONTINUE.equals(consoleInput)) {
                    isBotPaused = false;
                }

                if (!isBotPaused) {
                    if (!responseIterator.hasNext()) {
                        Collections.shuffle(botResponses);
                        responseIterator = botResponses.iterator();
                    }
                    String answer = responseIterator.next();
                    if (isRunning) {
                        System.out.println(answer);
                        chatLog.add("bot: " + answer);
                    }
                }
            }
        }
        saveLog(chatLog);
    }

    private List<String> readPhrases() {
        if (botAnswers == null || botAnswers.isEmpty()) {
            throw new IllegalStateException("BotAnswer file is not set or empty.");
        }

        List<String> strings;
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            strings = reader.lines()
                    .collect(Collectors
                            .toCollection(ArrayList::new));

        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to open file: " + botAnswers, e);
        }
        Collections.shuffle(strings);
        return strings;
    }

    private void saveLog(List<String> chatLog) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (String string : chatLog) {
                writer.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to save chat log to path: " + path);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/log_console_chat.txt", "./data/bot_responses_console_chat.txt");
        consoleChat.run();
    }
}
