package com.smola;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class FileReaderTest {
    private static final String TEST_FILE_NAME = "src/test/resources/clients.txt";
    FileReader fileReader;

    @BeforeClass
    public void setUp() {
        fileReader = new FileReader();
    }

    @Test
    public void shouldReadALlLinesFromFile() {
        List<String> lines = fileReader.readFileLines(TEST_FILE_NAME);
        assertThat(lines.size()).isEqualTo(7);
    }

    @Test
    public void shouldThrowException_whenFileNotFound() {
        String nonExistingFile = "blabla";

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> fileReader.readFileLines(nonExistingFile));
    }

    @Test
    public void shouldReadClientsFromFile() {
        List<Client> clients = fileReader.readClients(TEST_FILE_NAME);
        assertThat(clients.size()).isEqualTo(4);
    }

    @Test
    public void shouldReadClientsWithTheirORders() {
        List<Client> clients = fileReader.readClients(TEST_FILE_NAME);

        Client client = clients.stream()
                .filter(e -> e.equals(new Client("c00001", "Kowalski Jan")))
                .findFirst().get();

        assertThat(client.getOrders().size()).isEqualTo(3);
    }
}