package com.smola.engineers;

import java.util.Collection;

public interface Sorter<R,T> {
    R sort(Collection<? extends T> toSort);
}
