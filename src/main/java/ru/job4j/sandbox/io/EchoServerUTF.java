package ru.job4j.sandbox.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServerUTF {

    public static void main(String[] args) throws IOException {
        Pattern msgPattern = Pattern.compile(".*\\?msg=([^&]+).*");
        Matcher msgMatcher;
        final int HELLO_MSG_WORD_COUNT = 2;
        final int WORDS_TO_DROP_AT_END = 1;

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                try (OutputStream output = clientSocket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8))) {
                    output.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
                    output.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes(StandardCharsets.UTF_8));
                    output.write("\r\n".getBytes(StandardCharsets.UTF_8));

                    for (String line = input.readLine(); line != null && !line.isEmpty(); line = input.readLine()) {
                        msgMatcher = msgPattern.matcher(line);
                        String[] msgValues = null;

                        if (msgMatcher.find()) {
                            msgValues = URLDecoder.decode(msgMatcher.group(1), StandardCharsets.UTF_8).split(" ");
                        }

                        if (msgValues != null && "Exit".equals(msgValues[0])) {
                            serverSocket.close();
                        } else if (msgValues != null && msgValues.length == HELLO_MSG_WORD_COUNT
                                && "Hello".equals(msgValues[0])) {
                            output.write("Hello\n".getBytes());
                        } else if (msgValues != null) {
                            for (int i = 0; i < msgValues.length - WORDS_TO_DROP_AT_END; i++) {
                                output.write((msgValues[i] + " ").getBytes(StandardCharsets.UTF_8));
                            }
                        }
                    }
                    output.flush();
                }
            }
        }
    }
}
