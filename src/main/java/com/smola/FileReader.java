package com.smola;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class FileReader {

    List<String> readFileLines(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            return lines.collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("No such file!");
        }
    }

    public List<Line> split(List<String> allLines, String delimiter) {
        return Collections.emptyList();
    }

    public List<Client> readClients(String fileName) {
        List<String> strings = this.readFileLines(fileName);
        // todo: export it to the file?
        Integer clientIdColumn = 0;
        Integer clientFullNameColumn = 1;
        Integer productNameColumn = 2;
        Integer productPriceColumn = 4;
        List<Client> clients = new ArrayList<>();
        Integer nbOfProductsColumn = 4;
        List<String[]> splittedLines = strings.stream()
                .map(e -> e.split(";"))
                .collect(toList());


        for (String[] singleLine : splittedLines) {
            Client client = new Client(singleLine[clientIdColumn], singleLine[clientFullNameColumn]);
            Order singleLineOrder = new Order(singleLine[productNameColumn], new BigDecimal(singleLine[productPriceColumn]), Double.valueOf(singleLine[nbOfProductsColumn]));

            // todo: is it possible to make it prettier? :(
            if (clients.contains(client)) {
                clients.stream()
                        .filter(e -> e.equals(client))
                        .forEach(e -> e.addOrder(singleLineOrder));
            } else {
                client.addOrder(singleLineOrder);
                clients.add(client);
            }
        }

        return clients;
    }
}
