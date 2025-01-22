package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReader {
    private static final String OUTPUT_TO_STDOUT = "stdout";
    private static final int MAX_DELIMITER_LENGTH = 1;

    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        String outputPath = argsName.get("out");
        String[] requestedColumns = argsName.get("filter").split(",");
        StringJoiner finalResult = new StringJoiner(System.lineSeparator());

        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            String[] headers = scanner.nextLine().trim().split(delimiter);
            Map<String, Integer> columnIndexes = initiateRequestedColumnIndexes(requestedColumns, headers);

            finalResult.add(filterRow(headers, columnIndexes, delimiter));

            while (scanner.hasNext()) {
                String csvLine = scanner.nextLine().trim();
                if (csvLine.isEmpty()) {
                    continue;
                }

                String[] currentRow = csvLine.split(delimiter);
                finalResult.add(filterRow(currentRow, columnIndexes, delimiter));
            }
        }

        if (OUTPUT_TO_STDOUT.equals(outputPath)) {
            System.out.println(finalResult);
        } else {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath))) {
                writer.println(finalResult);
            }
        }
    }

    private static Map<String, Integer> initiateRequestedColumnIndexes(String[] requestedColumns, String[] headers) {
        Map<String, Integer> requestedColumnIndexes = new LinkedHashMap<>();
        Map<String, Integer> headerMap = IntStream
                .range(0, headers.length)
                .boxed()
                .collect(Collectors.toMap(
                        i -> headers[i].trim(), i -> i, (oldVal, newVal) -> oldVal));

        for (String column : requestedColumns) {
            if (!headerMap.containsKey(column)) {
                throw new IllegalArgumentException("Column not found in header: " + column);
            }
            requestedColumnIndexes.put(column, headerMap.get(column));
        }
        return requestedColumnIndexes;
    }

    private static String filterRow(String[] row, Map<String, Integer> targetIndexes, String delimiter) {
        StringJoiner result = new StringJoiner(delimiter);
        for (Integer index : targetIndexes.values()) {
            if (index >= row.length) {
                throw new IllegalArgumentException(
                        "Row contains fewer columns then expected: " + Arrays.toString(row));
            }
            result.add(row[index]);
        }
        return result.toString();
    }

    private static void validate(ArgsName argsName) throws IOException {

        String path = argsName.get("path");
        if (!Files.exists(Path.of(path))) {
            throw new IOException("Invalid input file path: " + path);
        }

        String out = argsName.get("out");
        if (!OUTPUT_TO_STDOUT.equals(out)) {
            Path outputPath = Path.of(out);
            if (Files.notExists(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }
        }

        String delimiter = argsName.get("delimiter");
        if (delimiter.length() > MAX_DELIMITER_LENGTH) {
            throw new IOException("Delimiter must be a single character: " + delimiter);
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
