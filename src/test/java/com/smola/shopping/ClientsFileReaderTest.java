package com.smola.shopping;

import com.smola.FileReader;
import com.smola.shopping.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

public class ClientsFileReaderTest {
    private static final String TEST_FILE_NAME = "src/test/resources/clients-test.txt";
    ClientsFileReader fileReader;
    private ClientRepository clientRepository;

    @BeforeClass
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        fileReader = new ClientsFileReader(clientRepository);
    }

    @Test
    public void shouldReadALlLinesFromFile() throws IOException {
        List<String> lines = fileReader.loadFileIntoStream(TEST_FILE_NAME).collect(toList());
        assertThat(lines.size()).isEqualTo(7);
    }

    @Test
    public void shouldThrowException_whenFileNotFound() {
        String nonExistingFile = "blabla";

        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> fileReader.loadFileIntoStream(nonExistingFile));
    }

    @Test
    public void shouldReadClientsFromFile() {
        List<Client> clients = fileReader.readClients(TEST_FILE_NAME);
        assertThat(clients.size()).isEqualTo(4);
    }

    @Test
    public void shouldReadClientsWithTheirORders() {
        String clientUnderTestId = "c00001";
        String clientUnderTestFullname = "Kowalski Jan";
        List<Client> allClients = fileReader.readClients(TEST_FILE_NAME);

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