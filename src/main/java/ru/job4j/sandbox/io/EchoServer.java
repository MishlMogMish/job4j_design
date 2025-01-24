package ru.job4j.sandbox.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile(".*/\\?msg=([\\S]+).*");
        Matcher matcher;

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String string = input.readLine();
                         string != null && !string.isEmpty(); string = input.readLine()) {
                        String msgValue = "";
                        matcher = pattern.matcher(string);

                        if (matcher.find()) {
                            msgValue = matcher.group(1).split(" ")[0];
                        }

                        if ("Bye".equals(msgValue)) {
                            server.close();
                        }
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}
