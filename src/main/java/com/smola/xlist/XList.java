package com.smola.xlist;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class XList<E> implements List<E> {
    private List<E> list = new ArrayList<>();

    public XList(E... elements) {
        this.list.addAll(Arrays.asList(elements));
    }

    public XList(Collection<? extends E> collection) {
        this.list.addAll(collection);
    }

    public static <T> XList<T> of(T... elements) {
        return new XList<>(elements);
    }

    // E cannot be referenced from static context!!!
    public static <T> XList<T> of(Collection<? extends T> collection) {
        return new XList<>(collection);
    }

    public static XList<String> charsOf(String string) {
        return of(string.chars().mapToObj(e -> String.valueOf((char) e))
                .collect(toList()));
    }

    public static XList<String> tokensOf(String string) {
        String emptySpace = " ";
        return tokensOf(string, emptySpace);
    }

    public static XList<String> tokensOf(String string, String delimiter) {
        return of(string.split(delimiter));
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public XList<E> union(Collection<E> collection) {
        List<E> copy = new ArrayList<>(this.list);
        copy.addAll(collection);
        return new XList<>(copy);
    }

    public XList<E> union(E[] e) {
        return union(Arrays.asList(e));
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return this.list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return this.list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
    }

    @Override
    public E remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }


    public XList diff(Collection<E> set) {
        List<E> diffs = new ArrayList<>(this);
        diffs.removeAll(set);
        return of(diffs);
    }

    public XList<E> unique() {
        return of(this.stream()
                .distinct()
                .collect(Collectors.toList()));
    }


    //todo: is it correct?
    public <R> XList<R> collect(Function<E, R> function) {
        List<R> collect = this.stream()
                .map(function::apply)
                .collect(toList());
        return of(collect);
    }

    public String join() {
        return this.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    public String join(String delimiter) {
        return this.stream()
                .map(String::valueOf)
                .collect(joining(delimiter));
    }

    public void forEachWithIndex(BiConsumer<E, Integer> biConsumer) {
        for (int i = 0; i < this.size(); i++) {
            biConsumer.accept(this.get(i), i);
        }
    }


}
