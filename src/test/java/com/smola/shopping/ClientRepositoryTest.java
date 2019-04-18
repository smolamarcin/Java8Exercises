package com.smola.shopping;

import com.smola.shopping.Client;
import com.smola.shopping.ClientRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientRepositoryTest {
    private ClientRepository clientRepository;

    private Set<Client> clients;

    @BeforeClass
    public void setUp() {
        clients = new HashSet<>(Arrays.asList(new Client("1", "Jan Kowalski", emptyList()), new Client("2", "Jan Nowak", emptyList())));
        clientRepository = new ClientRepository(clients);
    }

    @Test
    public void shouldFindByFullName() {
        Optional<Client> foundClient = clientRepository.findByFullName("Jan Kowalski");

        assertThat(foundClient).isNotEmpty();
        assertThat(foundClient.get().getFullName()).isEqualTo("Jan Kowalski");

    }
}