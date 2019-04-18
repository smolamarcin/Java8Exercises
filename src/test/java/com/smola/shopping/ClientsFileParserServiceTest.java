package com.smola.shopping;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ClientsFileParserServiceTest {
    private static final String TEST_FILE_NAME = "src/test/resources/clients-test.txt";
    ClientsFileParser fileParser;

    @BeforeClass
    public void setUp() {
        fileParser = new ClientsFileParser();
    }


    @Test
    public void shouldThrowException_whenFileNotFound() {
        String nonExistingFile = "blabla";

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> fileParser.parse(nonExistingFile));
    }

    @Test
    public void shouldReadClientsFromFile() {
        Collection<Client> clients = fileParser.parse(TEST_FILE_NAME);
        assertThat(clients.size()).isEqualTo(4);
    }

    @Test
    public void shouldReadClientsWithTheirORders() {
        String clientUnderTestId = "c00001";
        String clientUnderTestFullname = "Kowalski Jan";
        Collection<Client> allClients = fileParser.parse(TEST_FILE_NAME);

        Client clientUnderTest = allClients.stream()
                .filter(e -> e.equals(new Client(clientUnderTestId, clientUnderTestFullname, Collections.emptyList())))
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