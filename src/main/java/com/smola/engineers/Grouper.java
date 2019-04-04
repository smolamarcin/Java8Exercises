package com.smola.engineers;

import java.util.Collection;
import java.util.List;

public interface Grouper<R, T> {
    R group(Collection<T> collection);
}
