package com.smola;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class ClientRepositoryTest {
    private ClientRepository clientRepository;

    private Set<Client> clients;

    @BeforeClass
    public void setUp() {
        clients = new HashSet<>(Arrays.asList(new Client("1", "Jan Kowalski"), new Client("2", "Jan Nowak")));
        clientRepository = new ClientRepository(clients);
    }

    @Test
    public void shouldFindByFullName() {
        Optional<Client> foundClient = clientRepository.findByFullName("Jan Kowalski");

        assertThat(foundClient).isNotEmpty();
        assertThat(foundClient.get().getFullName()).isEqualTo("Jan Kowalski");

    }
}