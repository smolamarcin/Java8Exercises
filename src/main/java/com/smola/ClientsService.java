package com.smola;

import java.util.Optional;

class ClientsService {
    private ClientRepository clientRepository;

    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    Client findByFullName(String fullName) {
        Optional<Client> byFullName = clientRepository.findByFullName(fullName);
        return byFullName.orElseThrow(() -> new RuntimeException("CLient does not exists!"));
    }
}
