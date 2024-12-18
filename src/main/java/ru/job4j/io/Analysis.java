package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean isPaused = false;
        StringBuilder pauseTimeIntervals = new StringBuilder();
        final String TIME_REGEX = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] statusAndTime = line.split(" ", 2);

                if (statusAndTime.length < 2 || !statusAndTime[1].matches(TIME_REGEX)) {
                    throw new IllegalArgumentException("Invalid line format" + line);
                }
                int status = Integer.parseInt(statusAndTime[0]);

                if (status >= 400 && !isPaused) {
                    pauseTimeIntervals.append(statusAndTime[1]).append(";");
                    isPaused = true;
                } else if (status < 400 && isPaused) {
                    pauseTimeIntervals.append(statusAndTime[1]).append(";\n");
                    isPaused = false;
                }
            }
            writer.println(pauseTimeIntervals);

        } catch (IOException e) {
            throw new IllegalStateException("Error reading or writing file", e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
