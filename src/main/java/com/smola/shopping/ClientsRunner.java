package com.smola.shopping;

import java.util.HashSet;

public class ClientsRunner {
    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository(new HashSet<>());
        ClientsFileReader clientsFileReader = new ClientsFileReader();
        ClientsService clientsService = new ClientsService(clientRepository, clientsFileReader);

        clientRepository.save(clientsFileReader.parseClients("src/main/resources/clients.txt"));
        System.out.println(clientsService.getAll());
    }
}
