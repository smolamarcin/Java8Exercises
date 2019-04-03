package com.smola.shopping;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class ClientsFileReader {
    private static final String DELIMITER = ";";
    private static final Integer CLIENT_ID_COLUMN = 0;
    private static final Integer CLIENT_FULL_NAME_COLUMN = 1;
    private static final Integer PRODUCT_NAME_COLUMN = 2;
    private static final Integer PRODUCT_PRICE_COLUMN = 4;
    private static final Integer NB_OF_PRODUCTS_COLUMN = 4;

    List<Client> parseClients(String fileName) {
        List<Client> clients = new ArrayList<>();
        try {
            Files.lines(Paths.get(fileName))
                    .map(e -> e.split(DELIMITER))
                    .forEach(singleLine -> parseClientsFile(clients, singleLine));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }

        return clients;
    }

    private void parseClientsFile(List<Client> clients, String[] singleLine) {
        Client client = new Client(singleLine[CLIENT_ID_COLUMN], singleLine[CLIENT_FULL_NAME_COLUMN]);
        Order singleLineOrder = new Order(
                new Product(singleLine[PRODUCT_NAME_COLUMN], new BigDecimal(singleLine[PRODUCT_PRICE_COLUMN])), Double.valueOf(singleLine[NB_OF_PRODUCTS_COLUMN]));
        updateClientOrders(clients, client, singleLineOrder);
    }

    private void updateClientOrders(List<Client> clients, Client client, Order singleLineOrder) {
        clients.stream()
                .filter(e -> e.equals(client))
                .findFirst()
                .map(e -> e.addOrder(singleLineOrder))
                .orElseGet(() -> {
                    client.addOrder(singleLineOrder);
                    return clients.add(client);
                });
    }
}
