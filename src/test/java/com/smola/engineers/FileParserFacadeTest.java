package com.smola.engineers;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.Test;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


@PrepareForTest(FileType.class)
public class FileParserFacadeTest extends PowerMockTestCase {

    @Test
    public void shouldCallCsvParser_whenCsvFileComes() {
        mockStatic(FileType.class);
        when(FileType.of("csv")).thenReturn(FileType.CSV);
        String sampleCsvFile = "sadasd.csv";
        FileParserFacade facade = new FileParserFacade();
        facade.parseFile(sampleCsvFile);
    }
}