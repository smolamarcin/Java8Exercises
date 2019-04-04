package com.smola.engineers;

import java.util.Collection;

interface FileParser<R> {
    Collection<R> parseFile();
}
