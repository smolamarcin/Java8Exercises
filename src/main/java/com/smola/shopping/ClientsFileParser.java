package com.smola.shopping;


import com.smola.engineers.Parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class ClientsFileParser implements Parser<Client> {
    private static final String DELIMITER = ";";
    private static final Integer CLIENT_ID_COLUMN = 0;
    private static final Integer CLIENT_FULL_NAME_COLUMN = 1;
    private static final Integer PRODUCT_NAME_COLUMN = 2;
    private static final Integer PRODUCT_PRICE_COLUMN = 4;
    private static final Integer NB_OF_PRODUCTS_COLUMN = 4;

    public Collection<Client> parse(String fileName) {
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
        Client client = new Client(singleLine[CLIENT_ID_COLUMN], singleLine[CLIENT_FULL_NAME_COLUMN], Arrays.asList(new Order(
                new Product(singleLine[PRODUCT_NAME_COLUMN], new BigDecimal(singleLine[PRODUCT_PRICE_COLUMN])), Double.valueOf(singleLine[NB_OF_PRODUCTS_COLUMN]))));
        updateClientOrders(clients, client);
    }

    private void updateClientOrders(List<Client> clients, Client client) {
        clients.stream()
                .filter(e -> e.equals(client))
                .findFirst()
                .map(alreadyExistingClient -> {
                    alreadyExistingClient.addOrder(client.getOrders());
                    return alreadyExistingClient;
                })
                .orElseGet(() -> {
                    clients.add(client);
                    return client;
                });
    }

}
