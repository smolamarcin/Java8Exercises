package com.smola.engineers;

import java.util.Collection;

public interface Grouper<R, T> {
    R group(Collection<T> toSort);

}
