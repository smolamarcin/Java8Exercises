package com.smola.engineers;

import java.util.Collection;

interface Parser<T> {
    Collection<T> parse(String fileName);
}

