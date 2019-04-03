package com.smola.engineers;

import java.util.List;

public interface Grouper<R> {
    R group(List<String[]> lines);
}
