package com.smola.engineers;

import java.util.Collection;

abstract class AbstractFileParser<E> {
    String fileName;

    public AbstractFileParser(String fileName) {
        this.fileName = fileName;
    }

    public abstract Collection<E> parseFile();

}
