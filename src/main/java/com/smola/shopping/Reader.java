package com.smola.shopping;

public interface Reader<T> {
    T parse(String fileName);
}
