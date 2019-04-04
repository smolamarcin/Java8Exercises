package com.smola.engineers;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

interface Filter<K, V, E> {

    Map<K, V> filter(Collection<E> toFilter, Predicate<? super E> predicate);


}
