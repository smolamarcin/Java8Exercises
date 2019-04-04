package com.smola.engineers;

import java.util.Collection;

class UnknownTypeFileService {
    public static Collection<Programmer> parse(String fileName) {
        throw new IllegalArgumentException("This extension is not supported.");
    }
}
