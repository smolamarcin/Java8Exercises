package com.smola;

import java.util.*;

class ClientRepository {
    private Set<Client> clients;

    public ClientRepository(Set<Client> clients) {
        this.clients = clients;
    }

    public Optional<Client> findByFullName(String fullName) {
        return findAll()
                .stream()
                .filter(e -> e.getFullName().equalsIgnoreCase(fullName))
                .findFirst();
    }

    public Collection<Client> findAll() {
        return Collections.unmodifiableCollection(clients);
    }
}
