package com.smola.engineers;

import java.util.Collection;

public class FileParserFacade {

    Collection<Programmer> parseFile(String fileName) {
        FileType fileType = detectFileType(fileName);
        return fileType.parsingStrategy.apply(fileName);
    }

    private FileType detectFileType(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return FileType.of(extension.toUpperCase());
    }

}
