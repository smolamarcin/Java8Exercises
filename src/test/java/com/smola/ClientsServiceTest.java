package com.smola;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ClientsServiceTest {
    private ClientRepository clientRepository;
    private ClientsService clientsService;
    @BeforeClass
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientsService = new ClientsService(clientRepository);
    }

    @Test
    public void shouldFindClientByFullName() {
        String clientName = "Jan Kowalski";
        when(clientRepository.findByFullName(anyString())).thenReturn(java.util.Optional.of(new Client("1", clientName)));

        Client foundedClient = clientsService.findByFullName(clientName);

        assertThat(foundedClient.getFullName()).isEqualTo(clientName);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        when(clientRepository.findByFullName(anyString())).thenReturn(Optional.empty());
        assertThatThrownBy(()->clientsService.findByFullName("Non existing name!"))
                .isInstanceOf(RuntimeException.class);
    }
}