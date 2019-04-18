package com.smola.engineers;

import java.util.Collection;

public interface Parser<T> {
    Collection<T> parse(String fileName);
}

