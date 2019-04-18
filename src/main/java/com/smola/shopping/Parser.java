package com.smola.shopping;

public interface Parser<T> {
    T parse(String fileName);
}
