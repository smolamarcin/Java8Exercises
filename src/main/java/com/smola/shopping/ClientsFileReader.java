package com.smola.shopping;

import com.smola.FileReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ClientsFileReader implements FileReader {
    private static final String DELIMITER = ";";
    private static final Integer CLIENT_ID_COLUMN = 0;
    private static final Integer CLIENT_FULL_NAME_COLUMN = 1;
    private static final Integer PRODUCT_NAME_COLUMN = 2;
    private static final Integer PRODUCT_PRICE_COLUMN = 4;
    private static final Integer NB_OF_PRODUCTS_COLUMN = 4;

    private final ClientRepository clientRepository;

    ClientsFileReader(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    List<Client> readClients(String fileName) {
        List<Client> clients = new ArrayList<>();
        try (Stream<String> linesStream = loadFileIntoStream(fileName)) {
            linesStream
                    .map(e -> e.split(DELIMITER))
                    .forEach(singleLine -> {
                        Client client = new Client(singleLine[CLIENT_ID_COLUMN], singleLine[CLIENT_FULL_NAME_COLUMN]);
                        Order singleLineOrder = new Order(
                                new Product(singleLine[PRODUCT_NAME_COLUMN], new BigDecimal(singleLine[PRODUCT_PRICE_COLUMN])), Double.valueOf(singleLine[NB_OF_PRODUCTS_COLUMN]));
                        updateClientOrders(clients, client, singleLineOrder);
                    });
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }


        return clients;
    }

    private void updateClientOrders(List<Client> clients, Client client, Order singleLineOrder) {
        if (clients.contains(client)) {
            clients.stream()
                    .filter(e -> e.equals(client))
                    .forEach(e -> e.addOrder(singleLineOrder));
        } else {
            client.addOrder(singleLineOrder);
            clients.add(client);
        }
    }
}
