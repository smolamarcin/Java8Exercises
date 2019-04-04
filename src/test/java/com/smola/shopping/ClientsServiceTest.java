package com.smola.shopping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static com.smola.shopping.ClientsProvider.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientsServiceTest {
    private ClientRepository clientRepository;
    private ClientsService clientsService;
    private Set<Client> clients;
    private ClientsFileReader clientsFileReader;

    @BeforeClass
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientsFileReader = mock(ClientsFileReader.class);
        clientsService = new ClientsService(clientRepository, clientsFileReader);
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
        assertThatThrownBy(() -> clientsService.findByFullName("Non existing name!"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void shouldReturnAllClientOrders() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT, THIRD_CLIENT));

        Collection<Order> actual = clientsService.getAllClientOrder(FIRST_CLIENT);

        assertThat(actual).isEqualTo(FIRST_CLIENT_ORDERS);
    }

    @Test
    public void shouldReturnClients_SortedByNames_andThenByIds() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(FIRST_CLIENT, SECOND_CLIENT, THIRD_CLIENT));

        Collection<Client> clientsSortedByFullnames = clientsService.getClientsSortedByFullnames();

        assertThat(clientsSortedByFullnames).isEqualTo(Arrays.asList(THIRD_CLIENT, SECOND_CLIENT, FIRST_CLIENT));
    }

    @Test
    public void shouldCalculateClientTotalOrderPrice() {
        BigDecimal expected = new BigDecimal(420);
        BigDecimal actual = clientsService.calculateClientTotalOrderPrice(THIRD_CLIENT);

        assertThat(expected).isEqualByComparingTo(actual);
    }

    @Test
    public void shouldReturnClients_SortedByTotalOrdersPrice() {
        Client clientWhichSpentMostMoney = THIRD_CLIENT;

        Collection<Client> actual = clientsService.getClientsSortedByOrdersValue();

        assertThat(actual).isEqualTo(Arrays.asList(clientWhichSpentMostMoney, FIRST_CLIENT, SECOND_CLIENT));
    }
}