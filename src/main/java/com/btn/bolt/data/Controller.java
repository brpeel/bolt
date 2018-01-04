package com.btn.bolt.data;

import java.util.List;

public interface Controller<T> {
    T get(long id);
    default long create(T obj) { return 0; }
    default void delete(long id) {}
}
