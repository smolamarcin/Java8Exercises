package com.smola.engineers;

import java.util.Collection;

class UnknownTypeParser implements Parser<Programmer> {
    public Collection<Programmer> parse(String fileName) {
        throw new IllegalArgumentException("This extension is not supported.");
    }
}
