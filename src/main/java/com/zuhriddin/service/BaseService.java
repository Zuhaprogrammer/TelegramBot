package com.zuhriddin.service;

import java.util.*;

public interface BaseService<T, R> {

    T add(T t);

    T get(R id);

    void delete(R id);

    List<T> list();
}
