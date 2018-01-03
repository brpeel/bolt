package com.btn.bolt.data;

import java.util.List;

public interface Controller<T> {
    T get(long id);
    default boolean insert(T obj) { return false; }
    default boolean delete(long id) {return false; }
}
