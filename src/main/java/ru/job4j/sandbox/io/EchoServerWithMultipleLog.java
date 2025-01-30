package ru.job4j.sandbox.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServerWithMultipleLog {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServerWithMultipleLog.class.getName());

    public static void main(String[] args) {
        final int HELLO_MSG_WORD_COUNT = 2;
        final int WORDS_TO_DROP_AT_END = 1;

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            while (!serverSocket.isClosed()) {

                Socket clientSocket = serverSocket.accept();
                LOG.info("New client connected: {}", clientSocket.getInetAddress());

                try (OutputStream output = clientSocket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String requestLine = input.readLine();

                    if (requestLine == null) {
                        LOG.warn("Client closed the connection.");
                        continue;
                    }

                    Pattern msgPattern = Pattern.compile("GET.*\\?msg=([^&]+).*");
                    Matcher msgMatcher = msgPattern.matcher(requestLine);
                    String[] msgValues = null;

                    if (msgMatcher.find()) {
                        msgValues = URLDecoder.decode(msgMatcher.group(1), StandardCharsets.UTF_8).split(" ");
                    }

                    if (msgValues != null && "Exit".equals(msgValues[0])) {
                        LOG.info("Server is shutting down by client request.");
                        serverSocket.close();

                    } else if (msgValues != null && msgValues.length == HELLO_MSG_WORD_COUNT
                            && "Hello".equals(msgValues[0])) {
                        output.write("Hello\n".getBytes());
                    } else if (msgValues != null) {
                        for (int i = 0; i < msgValues.length - WORDS_TO_DROP_AT_END; i++) {
                            output.write((msgValues[i] + " ").getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Error  " + "Exception in EchoServer", e);
        }
    }
}
