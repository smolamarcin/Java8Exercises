package com.smola.shopping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

public class ClientsFileParserFacadeTest {
    private static final String TEST_FILE_NAME = "src/test/resources/clients-test.txt";
    ClientsFileReader fileReader;

    @BeforeClass
    public void setUp() {
        fileReader = new ClientsFileReader();
    }


    @Test
    public void shouldThrowException_whenFileNotFound() {
        String nonExistingFile = "blabla";

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> fileReader.parseClients(nonExistingFile));
    }

    @Test
    public void shouldReadClientsFromFile() {
        List<Client> clients = fileReader.parseClients(TEST_FILE_NAME);
        assertThat(clients.size()).isEqualTo(4);
    }

    @Test
    public void shouldReadClientsWithTheirORders() {
        String clientUnderTestId = "c00001";
        String clientUnderTestFullname = "Kowalski Jan";
        List<Client> allClients = fileReader.parseClients(TEST_FILE_NAME);

        Client clientUnderTest = allClients.stream()
                .filter(e -> e.equals(new Client(clientUnderTestId, clientUnderTestFullname)))
                .findFirst().get();

        List<Order> clientUnderTestOrders = clientUnderTest.getOrders();

        List<Product> orderedProducts = clientUnderTestOrders
                .stream()
                .map(Order::getProduct).collect(toList());

        assertThat(clientUnderTestOrders.size()).isEqualTo(3);
        assertThat(orderedProducts).extracting("productName")
                .containsOnly("milk", "roll", "milk");
    }


}