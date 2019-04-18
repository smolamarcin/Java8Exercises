package com.smola.shopping;

import java.util.HashSet;

public class ClientsRunner {
    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository(new HashSet<>());
        ClientsFileParser clientsFileReader = new ClientsFileParser();
        ClientsServiceImpl clientsService = new ClientsServiceImpl(clientRepository, clientsFileReader);

        clientRepository.save(clientsFileReader.parse("src/main/resources/clients.txt"));
        System.out.println(clientsService.getAll());
    }
}
