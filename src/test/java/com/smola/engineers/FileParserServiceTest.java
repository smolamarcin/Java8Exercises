package com.smola.engineers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.powermock.api.mockito.PowerMockito.mockStatic;


@PrepareForTest({CsvFileParser.class, TsvFileParser.class, UnknownTypeFileService.class})
public class FileParserServiceTest extends PowerMockTestCase {
    private FileParserService fileParserFacade;

    @BeforeMethod
    public void setUp() {
        fileParserFacade = new FileParserService();
    }

    @AfterMethod
    public void tearDown() {
        fileParserFacade = null;
    }

    @Test
    public void shouldCall_Csv_Parser_whenCsvFileComes() {
        // given
        PowerMockito.mockStatic(CsvFileParser.class);
        String sampleCsvFile = "sadasd.csv";

        // when
        fileParserFacade = new FileParserService();
        fileParserFacade.parseFile(sampleCsvFile);

        // then
        PowerMockito.verifyStatic(CsvFileParser.class);
    }

    @Test
    public void shouldCall_Tsv_Parser_whenCsvFileComes() {
        // given
        mockStatic(TsvFileParser.class);
        String sampleTsvFile = "sadasd.tsv";

        // when
        fileParserFacade.parseFile(sampleTsvFile);

        // then
        PowerMockito.verifyStatic(TsvFileParser.class);
    }

    @Test
    public void shouldCall_UnknownTypeParser_whenUnknownExtensionComes() {
        // given
        mockStatic(UnknownTypeFileService.class);
        String filnameeWithUnknownExtension = "asdasdads.xyz";

        // when
        fileParserFacade.parseFile(filnameeWithUnknownExtension);

        PowerMockito.verifyStatic(UnknownTypeFileService.class);

    }
}